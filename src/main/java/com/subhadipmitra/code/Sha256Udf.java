package com.subhadipmitra.code;
import javax.inject.Inject;
import com.google.common.hash.Hashing;
import org.apache.arrow.vector.holders.VarCharHolder;
import com.dremio.exec.expr.SimpleFunction;
import com.dremio.exec.expr.annotations.FunctionTemplate;
import com.dremio.exec.expr.annotations.FunctionTemplate.NullHandling;
import com.dremio.exec.expr.annotations.Output;
import com.dremio.exec.expr.annotations.Param;
import io.netty.buffer.ArrowBuf;
import java.nio.charset.StandardCharsets;

@FunctionTemplate(
        name = "sha256_udf",
        nulls = NullHandling.NULL_IF_NULL)

public class Sha256Udf {

    @Inject ArrowBuf buffer;
    @Param VarCharHolder input;
    @Output VarCharHolder out;

    @Override
    public void setup() {

    }

    @Override
    public void eval() {
        out.start = 0;
        charSequenceWrapper.setBuffer(input.start, input.end, input.buffer);
        final String inputString = com.dremio.exec.expr.fn.impl.StringFunctionHelpers
                .toStringFromUTF8(input.start, input.end, input.buffer);


        String sha256hexOutput = Hashing.sha256().hashString(inputString, StandardCharsets.UTF_8).toString();

        StringBuffer sb = new StringBuffer();
        sb.append(sha256hexOutput);

        final byte [] bytea = sb.toString().getBytes(java.nio.charset.Charset.forName("UTF-8"));
        out.buffer = buffer = buffer.reallocIfNeeded(bytea.length);
        out.buffer.setBytes(out.start, bytea);
        out.end = bytea.length;

    }

}
