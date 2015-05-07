package ninja.hikaru.kimoppi.lunandroid.support;

/**
 * Created by hikaru on 2015/05/07.
 */
public class FpsMoniter {

    long past;
    int wait;
    long[] pool;
    private long count;
    float fps;

    public FpsMoniter(int wait) {
        this.wait = wait;
        pool = new long[wait];
    }

    /**
     * –ˆƒtƒŒ[ƒ€ˆê‰ñ‚¸‚ÂŒÄ‚Ô‚±‚Æ
     * @return ‘ª’è‚µ‚½fps
     */
    public float show() {
        int i = (int)(count++ % wait);
        long now = System.nanoTime();
        pool[i] = now - past;
        past = now;

        if(i == 0) {
            long sum = 0L;
            for(long l : pool) {
                sum += l;
            }

            float ave = (float) sum / wait;
            fps = 1000000000 / ave;
        }

        return fps;
    }
}
