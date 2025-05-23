package com.porn.client.common.entity;

import java.io.Serializable;

public class Pair<K, V>
        implements Serializable {
    private K key;
    private V value;

    public Pair(K key, V value) {

        this.key = key;
        this.value = value;

    }

    public Pair() {
    }

    public static <K, V> PairBuilder<K, V> builder() {
        return new PairBuilder<>();
    }

    public static <K, V> Pair of(K key, V value) {

        return new Pair<>(key, value);

    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> other = (Pair<?, ?>) o;
        if (!other.canEqual(this)) return false;
        Object this$key = getKey(), other$key = other.getKey();
        if ((this$key == null) ? (other$key != null) : !this$key.equals(other$key)) return false;
        Object this$value = getValue(), other$value = other.getValue();
        return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Pair;
    }

    public String toString() {
        return "Pair(key=" + getKey() + ", value=" + getValue() + ")";
    }

    public K getKey() {

        return this.key;

    }

    public void setKey(K key) {

        this.key = key;
    }

    public V getValue() {

        return this.value;

    }

    public void setValue(V value) {
        this.value = value;
    }

    public static class PairBuilder<K, V> {
        private K key;
        private V value;

        public PairBuilder<K, V> key(K key) {
            this.key = key;
            return this;
        }

        public PairBuilder<K, V> value(V value) {
            this.value = value;
            return this;
        }

        public Pair<K, V> build() {
            return new Pair<>(this.key, this.value);
        }

        public String toString() {
            return "Pair.PairBuilder(key=" + this.key + ", value=" + this.value + ")";
        }
    }

}

