package rt;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class OneCheck {
    public static void main(String[] args) {
        final Flux<Integer> just = Flux.just(1, 2, 3, 4, 5);
        //just.log().subscribe();
        just.log()
            .map(i -> i * 2)
            .subscribe(new Subscriber<Integer>() {
                private Subscription subscription;
                @Override
                public void onSubscribe(Subscription subscription) {
                    System.out.println("OnSubscribe");
                    this.subscription = subscription;
                    this.subscription.request(3);
                }

                @Override
                public void onNext(Integer integer) {
                    System.out.println("OnNext");
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("OnError");
                }

                @Override
                public void onComplete() {
                    System.out.println("OnComplete");
                }
        });
    }
}
