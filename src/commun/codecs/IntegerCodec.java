package commun.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;

/**
 * Codec pour l'encryption et la décryption d'integer
 * @author Bank-era Corp.
 *
 */
public class IntegerCodec extends CryptableCodec<Integer> {

	@Override
	public void encode(BsonWriter writer, Integer value, EncoderContext encoderContext) {
		writer.writeString(AES.encrypter(cle, value.toString()));
	}

	@Override
	public Class<Integer> getEncoderClass() {
		return Integer.class;
	}

	@Override
	public Integer decode(BsonReader reader, DecoderContext decoderContext) {
		return Integer.valueOf(AES.decrypter(cle, reader.readString()));
	}

}
