package day16;

import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Tickets {
    Set<Ticket> tickets;
    Ticket myTicket;
    TicketSpec spec;

    Tickets() {
        spec = new TicketSpec();
        String[] specs = input[0].split("\n");
        for (String s : specs) {
            Set<TicketSpec.Range> ranges = new HashSet<>();
            String name = s.split(": ")[0];
            for(String r : s.split(": ")[1].split(" or ")) {
                int start = Integer.parseInt(r.split("-")[0]);
                int stop = Integer.parseInt(r.split("-")[1]);
                ranges.add(new TicketSpec.Range(start, stop));
            }
            spec.fieldRanges.put(name, ranges);
        }
        myTicket = new Ticket(Arrays.stream(input[1].split(":\n")[1].split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        tickets = Arrays.stream(input[2].split(":\n")[1].split("\n"))
                .map(x -> Arrays.stream(x.split(","))
                        .map(Integer::parseInt).collect(Collectors.toList()))
                .map(Ticket::new).collect(Collectors.toSet());
    }

    long discardInvalid() {
        long invalid_sum = 0;
        Set<Ticket> validTickets = new HashSet<>();
        Set<TicketSpec.Range> rangeCollection = new HashSet<>();
        this.spec.fieldRanges.keySet().stream()
                .map(x -> this.spec.fieldRanges.get(x))
                .forEach(rangeCollection::addAll);
        for(Ticket t : this.tickets) {
            boolean valid_ticket = true;
            for(int value : t.values) {
                boolean valid_field = false;
                for(TicketSpec.Range r : rangeCollection) {
                    if(value >= r.start && value <= r.stop) {
                        valid_field = true;
                        break;
                    }
                }
                invalid_sum = valid_field ? invalid_sum : invalid_sum + value;
                valid_ticket = valid_field && valid_ticket;
            }
            if(valid_ticket) validTickets.add(t);
        }
        this.tickets = validTickets;
        return invalid_sum;
    }

    long ex2() {
        Set<Ticket> allTickets = new HashSet<>(tickets);
        allTickets.add(myTicket);
        Map<Integer, Set<String>> possible_fields = new HashMap<>();
        for(int i = 0; i < allTickets.iterator().next().values.length; i++) {
            Set<String> fields = new HashSet<>();
            for(String field : this.spec.fieldRanges.keySet()) {
                boolean possible_field = true;
                for(Ticket t : allTickets) {
                    boolean possible_range = false;
                    for(TicketSpec.Range f_range : this.spec.fieldRanges.get(field)) {
                        if(t.values[i] >= f_range.start && t.values[i] <= f_range.stop) possible_range = true;
                    }
                    if(!possible_range) {
                        possible_field = false;
                        break;
                    }
                }
                if(possible_field) fields.add(field);
            }
            possible_fields.put(i, fields);
        }
        Map<String, Integer> final_field_mapping = new HashMap<>();
        while(final_field_mapping.keySet().size() != possible_fields.keySet().size()) {
            for(int i : possible_fields.keySet()) {
                if(possible_fields.get(i).size() == 1) {
                    String fieldName = possible_fields.get(i).iterator().next();
                    final_field_mapping.put(fieldName, i);
                    for(int j : possible_fields.keySet()) {
                        possible_fields.get(j).remove(fieldName);
                    }
                }
            }
        }
        return final_field_mapping.keySet().stream()
                .filter(x -> x.startsWith("departure"))
                .map(y -> myTicket.values[final_field_mapping.get(y)])
                .map(y -> (long) y)
                .reduce((v1, v2) -> v1 * v2)
                .get();
    }


    static String[] input;

    static {
        try {
            input = Util.processStringInput("/day16/input.txt", "\n\n");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        System.out.println("---ex1---");
        System.out.println(tickets.discardInvalid());
        System.out.println("---ex2---");
        System.out.println(tickets.ex2());
    }
}
