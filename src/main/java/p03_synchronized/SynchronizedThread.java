package p03_synchronized;

public class SynchronizedThread {

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[]{
                new StudentIncrementThread(),
                new StudentDecrementThread(),
                new TeacherIncrementThread(),
                new TeacherDecrementThread()
        };

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            t.join();
        }

        // threads used same locks, so count keeps the same (both are 0)
        System.out.println("Counter1.studentCount:  " + Counter1.studentCount);
        System.out.println("Counter1.teacherCount:  " + Counter1.teacherCount);
    }
}

class Counter1 {
    public static final Object LOCK_STUDENT = new Object();
    public static final Object LOCK_TEACHER = new Object();
    public static int studentCount = 0;
    public static int teacherCount = 0;
}

class StudentIncrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Counter1.LOCK_STUDENT) {
                Counter1.studentCount++;
            }
        }
    }
}

class StudentDecrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Counter1.LOCK_STUDENT){
                Counter1.studentCount--;
            }
        }
    }
}

class TeacherIncrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Counter1.LOCK_TEACHER) {
                Counter1.teacherCount++;
            }
        }
    }
}

class TeacherDecrementThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (Counter1.LOCK_TEACHER){
                Counter1.teacherCount--;
            }
        }
    }
}