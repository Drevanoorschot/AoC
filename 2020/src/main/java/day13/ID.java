package day13;

import java.util.*;
import java.util.stream.Collectors;

public class ID {
    long val;
    long offset;

    ID(long val, long offset) {
        this.val = val;
        this.offset = offset;
    }

    static ID deriveID(ID id1, ID id2) {
        long val = id1.offset;
        while (true) {
            if ((val + id2.offset) % id2.val == 0) {
                return new ID(id1.val * id2.val, val);
            }
            val += id1.val;
        }
    }

    static long ex2() {
        List<Long> ids = Arrays.stream(BusSchedule.input[1].split(",")).filter(x -> !x.equals("x")).map(Long::parseLong).collect(Collectors.toList());
        Map<Long, Long> id_offsets = new HashMap<>();
        List<Long> lookupArray = Arrays.stream(BusSchedule.input[1].split(",")).map(x -> x.equals("x") ? "-1" : x).map(Long::parseLong).collect(Collectors.toList());
        ids.forEach(id -> id_offsets.put((long) id, (long) lookupArray.indexOf(id)));

        List<ID> IDs = ids.stream().map(id -> new ID(id, id_offsets.get(id))).collect(Collectors.toList());
        ID work_id = deriveID(IDs.get(0), IDs.get(1));
        for(int i = 2; i < IDs.size(); i++) {
            work_id = deriveID(work_id, IDs.get(i));
        }
        return work_id.offset;
    }

    public static void main(String[] args) {
        ID id1 = new ID(3, 0);
        ID id2 = new ID(5, 1);
        ID id3 = new ID(7,2);
        ID id = deriveID(id1, id2);
        id = deriveID(id, id3);
        System.out.println(id.val);
        System.out.println(id.offset);

        System.out.println("---ex2---");
        System.out.println(ex2());
    }
}
