package huang.yong.chang.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class DoubleJsonDeserializer extends JsonSerializer<Double> {
    //原本这里是  ##.00 ,带来的问题是如果数据库数据为0.00返回“ .00 “经评论指正，改为0.00
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aDouble != null) {
            jsonGenerator.writeString(df.format(aDouble));
        }
    }
}
