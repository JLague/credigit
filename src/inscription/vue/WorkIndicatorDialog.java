package inscription.vue;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Public domain. Use as you like. No warranties. Return type is always Boolean.
 * (cc) @imifos
 */
public class WorkIndicatorDialog {

	private Task<Object> animationWorker;
	private Task<Boolean> taskWorker;

	private final ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
	private final Stage dialog = new Stage(StageStyle.UTILITY);
	private final Label label = new Label();
	private final Group root = new Group();
	private final Scene scene = new Scene(root, 330, 120, Color.WHITE);
	private final BorderPane mainPane = new BorderPane();
	private final VBox vbox = new VBox();

	/**
	 * Placing a listener on this list allows to get notified BY the result when the
	 * task has finished.
	 */
	public ObservableList<Boolean> resultNotificationList = FXCollections.observableArrayList();

	public Boolean resultValue;

	/**
	 *
	 */
	public WorkIndicatorDialog(Window owner, String label) {
		dialog.setTitle("Création du compte");
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(owner);
		dialog.setResizable(false);
		this.label.setText(label);
	}

	/**
	 * 
	 */
	public void addTaskEndNotification(Consumer<Boolean> c) {
		resultNotificationList.addListener((ListChangeListener<? super Boolean>) n -> {
			resultNotificationList.clear();
			c.accept(resultValue);
		});
	}

	/**
	 *
	 */
	public void exec(BooleanSupplier func) {
		setupDialog();
		setupAnimationThread();
		setupWorkerThread(func);
	}

	/**
	 *
	 */
	private void setupDialog() {
		root.getChildren().add(mainPane);
		vbox.setSpacing(5);
		vbox.setAlignment(Pos.CENTER);
		vbox.setMinSize(330, 120);
		vbox.getChildren().addAll(label, progressIndicator);
		mainPane.setTop(vbox);
		mainPane.getStylesheets().add(getClass().getResource("/styles/inscription/Loading.css").toExternalForm());
		mainPane.getStyleClass().add("loading");
		dialog.setScene(scene);

		dialog.setOnHiding(event -> {
			/*
			 * Gets notified when task ended, but BEFORE result value is attributed. Using
			 * the observable list above is recommended.
			 */ });

		dialog.show();
	}

	/**
	 *
	 */
	private void setupAnimationThread() {

		animationWorker = new Task<Object>() {
			@Override
			protected Object call() throws Exception {
				/*
				 * This is activated when we have a "progressing" indicator. This thread is used
				 * to advance progress every XXX milliseconds. In case of an
				 * INDETERMINATE_PROGRESS indicator, it's not of use. for (int i=1;i<=100;i++) {
				 * Thread.sleep(500); updateMessage(); updateProgress(i,100); }
				 */
				return true;
			}
		};

		progressIndicator.setProgress(0);
		progressIndicator.progressProperty().unbind();
		progressIndicator.progressProperty().bind(animationWorker.progressProperty());

		animationWorker.messageProperty().addListener((observable, oldValue, newValue) -> {
			// Do something when the animation value ticker has changed
		});

		new Thread(animationWorker).start();
	}

	/**
	 *
	 */
	private void setupWorkerThread(BooleanSupplier func) {

		taskWorker = new Task<Boolean>() {
			@Override
			public Boolean call() {
				return func.getAsBoolean();
			}
		};

		EventHandler<WorkerStateEvent> eh = event -> {
			animationWorker.cancel(true);
			progressIndicator.progressProperty().unbind();
			dialog.close();
			try {
				resultValue = taskWorker.get();
				resultNotificationList.add(resultValue);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};

		taskWorker.setOnSucceeded(eh);
		taskWorker.setOnFailed(eh);

		new Thread(taskWorker).start();
	}

	/**
	 * For those that like beans :)
	 */
	public boolean getResultValue() {
		return resultValue;
	}
}
