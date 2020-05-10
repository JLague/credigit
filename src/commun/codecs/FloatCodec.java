package commun.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;

/**
 * Codec pour l'encryption et la décryption de float
 * @author Bank-era Corp.
 *
 */
public class FloatCodec extends CryptableCodec<Float> {

	@Override
	public void encode(BsonWriter writer, Float value, EncoderContext encoderContext) {
		writer.writeString(AES.encrypter(cle, value.toString()));
	}

	@Override
	public Class<Float> getEncoderClass() {
		return Float.class;
	}

	@Override
	public Float decode(BsonReader reader, DecoderContext decoderContext) {
		return Float.valueOf(AES.decrypter(cle, reader.readString()));
	}
}
