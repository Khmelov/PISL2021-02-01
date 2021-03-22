package by.it.group873602.artishevskiy.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class B_Sheduler {
    static class Event implements Comparable<Event> {
        int start, stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
        }

        @Override
        public int compareTo(Event event) {
            return (this.stop - event.stop);
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = { new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5), new Event(1, 3),
                new Event(1, 3), new Event(1, 3), new Event(3, 6), new Event(2, 7), new Event(2, 3), new Event(2, 7),
                new Event(7, 9), new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7), new Event(4, 5),
                new Event(6, 7), new Event(6, 9), new Event(7, 9), new Event(8, 9), new Event(4, 6), new Event(8, 10),
                new Event(7, 10) };

        List<Event> starts = instance.calcStartTimes(events, 0, 10);
        System.out.println(starts);
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        Arrays.sort(events);
        int previousEnd = from;

        for (int i = 0; i < events.length; i++) {
            if (events[i].start >= previousEnd && events[i].stop <= to) {
                result.add(events[i]);
                previousEnd = events[i].stop;
            }
        }

        return result;
    }
}
