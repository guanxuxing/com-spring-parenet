import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018-09-30.
 */
public class TestClass {

    public static void main(String[] args){
        ArrayList list = new ArrayList(2000000000);
        for (int i = 0; i<list.size(); i++) {
            Map<String, String> m = new HashedMap();
            Map<String, String> map = new HashedMap();
            map.put(String.valueOf(i), UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString());
            m.put(String.valueOf(i), UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString()+UUID.randomUUID().toString());
            list.add(map);
            list.add(m);
        }
        System.out.println();
    }
}
