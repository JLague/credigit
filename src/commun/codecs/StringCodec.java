package commun.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;

/**
 * Codec pour l'encryption et la décryption de string
 * @author Bank-era Corp.
 *
 */
public class StringCodec extends CryptableCodec<String> {

	@Override
	public void encode(BsonWriter writer, String value, EncoderContext encoderContext) {
		writer.writeString(AES.encrypter(cle, value));
	}

	@Override
	public Class<String> getEncoderClass() {
		return String.class;
	}

	@Override
	public String decode(BsonReader reader, DecoderContext decoderContext) {
		String s = reader.readString();
		return cle == null ? s : AES.decrypter(cle, s);
	}
}
