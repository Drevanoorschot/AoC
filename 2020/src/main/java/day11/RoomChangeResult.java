package day11;

class RoomChangeResult {
    SeatState[][] newRoom;
    boolean changed;

    RoomChangeResult(SeatState[][] room, boolean changed) {
        this.newRoom = room;
        this.changed = changed;
    }
}
