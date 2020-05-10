package commun.codecs;

import java.time.LocalDate;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;

/**
 * Codec pour l'encryption et la décryption de Date
 * @author Bank-era Corp.
 *
 */
public class DateCodec extends CryptableCodec<LocalDate> {

	@Override
	public void encode(BsonWriter writer, LocalDate value, EncoderContext encoderContext) {
		writer.writeString(AES.encrypter(cle, value.toString()));
	}

	@Override
	public Class<LocalDate> getEncoderClass() {
		return LocalDate.class;
	}

	@Override
	public LocalDate decode(BsonReader reader, DecoderContext decoderContext) {
		return LocalDate.parse(AES.decrypter(cle, reader.readString()));
	}

}
