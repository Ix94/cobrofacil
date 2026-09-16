package com.cobrofacil.app;

import android.os.Bundle;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Draw edge-to-edge (required on Android 15+/API 35+) and let the
        // WebView report real values through CSS env(safe-area-inset-*),
        // so www/index.html can pad around the status bar and nav bar itself.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        // Dark app background -> light (white) status/nav bar icons.
        WindowInsetsControllerCompat controller =
                new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        controller.setAppearanceLightStatusBars(false);
        controller.setAppearanceLightNavigationBars(false);
    }
}
