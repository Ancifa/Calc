package com.example.demo;

import com.vaadin.ui.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by i on 18.12.2017.
 */
public class Timer implements Runnable{
    private HorizontalLayout layout;
    private Label secondsLabel;
    private Button startStopButton;
    private Button clearButton;

    private int counter;

    private Runnable thread;
    private ScheduledExecutorService executorService;

    public HorizontalLayout buildLayout() {
        layout = new HorizontalLayout();

        Label hoursLabel = new Label("00");
        Label separator1 = new Label(":");
        Label minutesLabel = new Label("00");
        Label separator2 = new Label(":");
        secondsLabel = new Label("00");
        startStopButton = new Button("Start");
        clearButton = new Button("Clear");

        setButtonListeners();

        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        layout.addComponents(hoursLabel, separator1, minutesLabel, separator2, secondsLabel,
                startStopButton, clearButton);

        return layout;
    }

    private void setButtonListeners() {
        startStopButton.addClickListener(clickEvent -> {
            if ("Start".equals(startStopButton.getCaption())) {
                startStopButton.setCaption("Stop");
                if (thread == null) {
                    thread = new TimerThread();
                    run();
                }
            } else {
                startStopButton.setCaption("Start");
                executorService.shutdown();
                thread = null;
            }
        });

        clearButton.addClickListener(clickEvent -> {
            counter = 0;
            secondsLabel.setValue(String.valueOf(counter));
        });
    }

    public void runTimer(Runnable thread) {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(thread, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        runTimer(thread);
    }

    private class TimerThread implements Runnable {

        @Override
        public void run() {
            counter++;
            secondsLabel.setValue(String.valueOf(counter));
        }
    }
}
