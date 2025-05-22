
package com.porn.client.common.entity;



import java.io.Serializable;



 public class PairPlus<K, V, D>
         implements Serializable
         {
       private K key;
       private V value;
       private D descrition;



    public void setKey(K key) {
        /* 14 */
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setDescrition(D descrition) {
        this.descrition = descrition;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PairPlus)) return false;
        PairPlus<?, ?, ?> other = (PairPlus<?, ?, ?>) o;
        if (!other.canEqual(this)) return false;
        Object this$key = getKey(), other$key = other.getKey();
        if ((this$key == null) ? (other$key != null) : !this$key.equals(other$key)) return false;
        Object this$value = getValue(), other$value = other.getValue();
        if ((this$value == null) ? (other$value != null) : !this$value.equals(other$value)) return false;
        Object this$descrition = getDescrition(), other$descrition = other.getDescrition();
        return !((this$descrition == null) ? (other$descrition != null) : !this$descrition.equals(other$descrition));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PairPlus;
    }


    public String toString() {
        return "PairPlus(key=" + getKey() + ", value=" + getValue() + ", descrition=" + getDescrition() + ")";
    }

    /* 15 */
    public static <K, V, D> PairPlusBuilder<K, V, D> builder() {
        return new PairPlusBuilder<>();
    }

    public static class PairPlusBuilder<K, V, D> {
        private K key;

        public PairPlusBuilder<K, V, D> key(K key) {
            this.key = key;
            return this;
        }

        private V value;
        private D descrition;

        public PairPlusBuilder<K, V, D> value(V value) {
            this.value = value;
            return this;
        }

        public PairPlusBuilder<K, V, D> descrition(D descrition) {
            this.descrition = descrition;
            return this;
        }

        public PairPlus<K, V, D> build() {
            return new PairPlus<>(this.key, this.value, this.descrition);
        }

        public String toString() {
            return "PairPlus.PairPlusBuilder(key=" + this.key + ", value=" + this.value + ", descrition=" + this.descrition + ")";
        }
    }

    public PairPlus(K key, V value, D descrition) {
        /* 16 */
        this.key = key;
        this.value = value;
        this.descrition = descrition;

    }


    public PairPlus() {
    }


    public K getKey() {
        /* 20 */
        return this.key;

    }

    public V getValue() {
        /* 22 */
        return this.value;

    }

    public D getDescrition() {
        /* 24 */
        return this.descrition;

    }












    public static <K, V, D> PairPlus of(K key, V value, D descrition) {
        /* 37 */
        return new PairPlus<>(key, value, descrition);

    }

}


