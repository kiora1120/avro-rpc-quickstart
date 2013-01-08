import example.proto.Mail;
import example.proto.Message;
import org.apache.avro.AvroRemoteException;
import org.apache.avro.ipc.NettyTransceiver;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.util.Utf8;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * A class representing a window on the screen.
 * For example:
 * <pre>
 *    Window win = new Window(parent);
 *    win.show();
 * </pre>
 *
 * @author <A HREF="mailto:[kiora1120@gmail.com]">TJune Kim</A>
 * @version 1.0
 */
public class SendTest {
    private Mail proxy;

    @Before
    public void init() throws IOException {
        NettyTransceiver client = new NettyTransceiver(new InetSocketAddress(65111));
        // client code - attach to the server and send a message
        proxy = (Mail) SpecificRequestor.getClient(Mail.class, client);
    }


    @Test
    public void testSend() throws AvroRemoteException {
        // fill in the Message record and send it
        Message message = new Message();
        message.setTo(new Utf8("TJune"));
        message.setFrom(new Utf8("Server"));
        message.setBody(new Utf8("Test Body"));
        System.out.println("Calling proxy.send with message:  " + message.toString());
        System.out.println("Result: " + proxy.send(message));
    }


}
