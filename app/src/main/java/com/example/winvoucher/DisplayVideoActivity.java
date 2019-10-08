package com.example.winvoucher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;


public class DisplayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    public static final String COUPON_VALUE = "com.example.winvoucher.COUPON_VALUE";
    final Random r = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        Button couponBtn = findViewById(R.id.coupon_button);
        couponBtn.setEnabled(false);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        Intent intent = getIntent();
        String videoId = intent.getStringExtra(MainActivity.VIDEO_ID);
        TextView textView = findViewById(R.id.video_id);
        textView.setText(videoId);

        YouTubePlayerView youTubeView = findViewById(R.id.videoView1);
        youTubeView.initialize("123456", this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);


        if (!wasRestored) player.loadVideo("IUtz-L-DJ2o"); // your video to play
    }
    @Override
    public void onInitializationFailure(Provider arg0,
        YouTubeInitializationResult arg1){
        Toast.makeText(this, "Oh no! "+arg1.toString(), Toast.LENGTH_LONG).show();

    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void displayCoupon(View view){
        showMessage("called displayCoupon method");
        Intent intent = new Intent(this, ShowCouponActivity.class);

        intent.putExtra(COUPON_VALUE, Integer.toString(r.nextInt(100)+1));
        startActivity(intent);

    }


    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().

        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().

        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.

            Button couponBtn = findViewById(R.id.coupon_button);
            couponBtn.setEnabled(true);

        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            Button couponBtn = findViewById(R.id.coupon_button);
            couponBtn.setEnabled(false);
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            Button couponBtn = findViewById(R.id.coupon_button);
            couponBtn.setEnabled(false);

            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {


            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }
}

