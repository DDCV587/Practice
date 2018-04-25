package com.ddc.zcy;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Pet {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pet(String type) {
        this.type = type;
    }

    public Pet() {
    }
}

class Dog extends Pet {
    public Dog(String type) {
        super(type);
    }
}

class Cat extends Pet {
    public Cat(String type) {
        super(type);
    }
}

class PeterEnterQueue<T> {
    private T pet;
    private long ts;

    public PeterEnterQueue(T pet, long ts) {
        this.pet = pet;
        this.ts = ts;
    }

    public T getPet() {
        return pet;
    }

    public void setPet(T pet) {
        this.pet = pet;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}

public class ZCY1_4<T> {
    Queue<PeterEnterQueue> dogQueue = new LinkedList<PeterEnterQueue>();

    Queue<PeterEnterQueue> catQueue = new LinkedList<PeterEnterQueue>();

    public void add(T animal) {
        PeterEnterQueue peq = new PeterEnterQueue(animal, System.currentTimeMillis());
        if (animal instanceof Dog) {
            dogQueue.add(peq);
        }

        if(animal instanceof Cat) {
            catQueue.add(peq);
        }
    }

    public Dog pollDog() {
        if(dogQueue.isEmpty()) {
            throw new RuntimeException("狗队列为空");
        }else {
            return ((PeterEnterQueue<Dog>)dogQueue.poll()).getPet();
        }
    }

    public Cat pollCat() {
        if(dogQueue.isEmpty()) {
            throw new RuntimeException("猫队列为空");
        }else {
            return ((PeterEnterQueue<Cat>)dogQueue.poll()).getPet();
        }
    }

    public Pet pollAll() {
        if(dogQueue.isEmpty() && catQueue.isEmpty()) {
            throw new RuntimeException("两队列为空");
        }else {
            if(dogQueue.isEmpty()) {
                return (Cat)(catQueue.poll().getPet());
            }else if(catQueue.isEmpty()) {
                return (Dog)(dogQueue.poll().getPet());
            }else {
                long dogTime = dogQueue.peek().getTs();
                long catTime = catQueue.peek().getTs();
                if(dogTime < catTime) {
                    return (Dog)(dogQueue.poll().getPet());
                } else if(dogTime > catTime) {
                    return (Cat)(catQueue.poll().getPet());
                }else {
                    catQueue.poll();
                    return (Dog)(dogQueue.poll().getPet());
                }
            }
        }
    }

}
