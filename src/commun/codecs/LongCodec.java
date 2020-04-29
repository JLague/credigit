package commun.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import commun.CryptableCodec;
import encryption.AES;

public class LongCodec extends CryptableCodec<Long> {

	@Override
	public void encode(BsonWriter writer, Long value, EncoderContext encoderContext) {
		writer.writeString(AES.encrypter(cle, value.toString()));
	}

	@Override
	public Class<Long> getEncoderClass() {
		return Long.class;
	}

	@Override
	public Long decode(BsonReader reader, DecoderContext decoderContext) {
		return Long.valueOf(AES.decrypter(cle, reader.readString()));
	}
}
