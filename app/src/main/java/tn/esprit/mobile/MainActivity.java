package tn.esprit.mobile;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;

import tn.esprit.mobile.ActivitiesUserManagement.ActivityLogin;
import tn.esprit.mobile.ActivitiesUserManagement.ActivityRegister;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Trouvez la vue du logo
        final View logoView = findViewById(R.id.log_1);

        // Chargez l'animation depuis le fichier XML
        Animation slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);

        // Appliquez l'animation à la vue du logo
        logoView.startAnimation(slideDownAnimation);

        // Ajoutez un écouteur d'animation pour effectuer des actions lorsque l'animation se termine
        slideDownAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Code à exécuter au début de l'animation
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Code à exécuter à la fin de l'animation
                // Vous pouvez ajuster la position du logo au centre ici
                centerLogo(logoView);
                openRegisterActivity();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Code à exécuter en cas de répétition de l'animation (si elle se répète)
            }
        });
    }

    private void centerLogo(View logoView) {
        // Obtenez les paramètres de mise en page actuels du logo
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) logoView.getLayoutParams();

        // Calculez les nouvelles marges pour centrer le logo horizontalement et verticalement
        int marginLeft = (logoView.getWidth() / 2) * -1;
        int marginTop = (logoView.getHeight() / 2) * -1;

        // Appliquez les nouvelles marges
        layoutParams.setMargins(0, 0, 0, 0);
        logoView.setLayoutParams(layoutParams);
    }
    private void openRegisterActivity() {
        // Créez un Intent pour ouvrir ActivityRegister
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);

        // Facultatif : Fermez MainActivity si vous ne voulez pas y retourner en appuyant sur le bouton "Retour"
        finish();
    }
}
