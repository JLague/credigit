package commun.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;
import inscription.modele.Questions;

public class EnumCodec extends CryptableCodec<Questions> {

		@Override
		public void encode(BsonWriter writer, Questions value, EncoderContext encoderContext) {
			writer.writeString(AES.encrypter(cle, value.getTexte()));
		}

		@Override
		public Class<Questions> getEncoderClass() {
			return Questions.class;
		}

		@Override
		public Questions decode(BsonReader reader, DecoderContext decoderContext) {
			return Questions.getQuestionFromString((AES.decrypter(cle, reader.readString())));
		}
}
