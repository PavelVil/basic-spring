package com.github.pavelvil.spring.core.proxy;

import com.github.pavelvil.spring.core.proxy.ben.Customer;
import com.github.pavelvil.spring.core.proxy.ben.IWaiter;
import com.github.pavelvil.spring.core.proxy.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Image image = new ProxyImage("sample.jpg");
        image.display();
        image.display();

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IWaiter john = context.getBean("john", IWaiter.class);
        Customer andrew = context.getBean("andrew", Customer.class);
        Customer julia = context.getBean("julia", Customer.class);
        Customer nina = context.getBean("nina", Customer.class);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> andrew.placeOrder(john)).start();
            new Thread(() -> julia.placeOrder(john)).start();
            new Thread(() -> nina.placeOrder(john)).start();
        }
    }

    interface Image {
        void display();
    }

    static class RealImage implements Image {
        private final String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    static class ProxyImage implements Image {
        private RealImage realImage;
        private final String filename;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

}
