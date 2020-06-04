import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.LoggingLevel;

public class MessageProcessor extends RouteBuilder {

    public void configure() throws Exception {
        from("kafka:{{kafka.topic}}?brokers={{kafka.address}}&groupId={{kafka.groupId}}&autoOffsetReset=earliest")
          .log(LoggingLevel.INFO, "${body}")
          .to("smtp://{{smtp.username}}@{{smtp.address}}?from=RAW({{smtp.from}})&to=RAW({{smtp.to}})&username=RAW({{smtp.username}})&password=RAW({{smtp.password}})")
        ;
    }
}
