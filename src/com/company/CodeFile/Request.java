package com.company.CodeFile;

public class Request<K, V> implements Comparable {
    private K key;
    private V value1, value2;
    private Double score;

    public Request(K key, V value1, V value2, Double score) {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.score = score;
    }

    public K getKey() {
        return key;
    }

    public V getValue1() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }

    public Double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "\nJob:" + key +
                "User:\n" + value1 +
                "Recruiter:\n" + value2 +
                " ; Score: " + score;
    }

    @Override
    public int compareTo(Object o) {
        Request<Job, User> r = (Request<Job, User>) o;
        if(this.getScore() >= r.getScore()) {
            return -1;
        } else {
            return 1;
        }
    }
}
