package org.wings_lab;

import com.att.m2x.java.M2XClient;
import com.att.m2x.java.M2XDevice;
import com.att.m2x.java.M2XStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * class for M2X config
 * Created by Wing on 20/2/2016.
 */
public class M2X {
    static M2XClient client = new M2XClient("43aef73936897db4e98f672755659b13");
    static M2XDevice device = client.device("1e0393a13b9ffef6325b9fb4bc2aac61");
    static M2XStream speed = device.stream("IsMoving");
    static M2XStream eyes = device.stream("IsEyeClosing");

    public static void UpdateSpeed(int mph) throws IOException {
        speed.updateValue(M2XClient.jsonSerialize(new HashMap<String, Object>() {{
            put("value", mph);
        }}));
    }

    public static void UpdateEyesDetection(int status) throws IOException {
        eyes.updateValue(M2XClient.jsonSerialize(new Hashtable<String, Object>() {{
            put("value", status);
        }}));
    }
}
