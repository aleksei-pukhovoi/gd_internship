package multithreading.executor.example1;

public enum CleaningType {
    DUST{
        @Override
        public void clean() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Dust is removed");
        }
    },
    MESS{
        @Override
        public void clean() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Mess is cleaned up");
        }
    }
    ,
    FLOOR{
        @Override
        public void clean() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The floors are scrubed");
        }
    },
    CLOTHES{
        @Override
        public void clean() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The clothes are washed");
        }
    };

    public abstract void clean();
}
