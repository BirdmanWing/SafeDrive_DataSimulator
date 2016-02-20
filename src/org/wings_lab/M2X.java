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
    M2XClient client = new M2XClient("4e51dd845f792519530b4d2d30e4172f");
    M2XDevice device = client.device("a10dd36dfa7718974755967a654e8a04");
    M2XStream speed = device.stream("speed");
    M2XStream eyes = device.stream("eyes_detection");

    public void updateSpeed(int mph) throws IOException {
        speed.updateValue(M2XClient.jsonSerialize(new HashMap<String, Object>()
        {{
            put("value", mph);
        }}));
    }

    public void updateEyesDetection(int status) throws IOException {
        eyes.updateValue(M2XClient.jsonSerialize(new Hashtable<String, Object>()
        {{
            put("value", status);
        }}));
    }
}
