package com.example.chchi.materialdesignapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.dropin.Customization;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class MainActivity extends ActionBarActivity {
        Button mBuyButton,mPP_buynow;
        AsyncHttpClient mHttpClient;
        String mClientToken;


    private static final int DROP_IN_REQUEST = 200;
    private static final int PAYPAL_REQUEST = 100;
        Myapplication myapplication = new Myapplication();
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AVo6vRBmro2mOMfFbrsWVZhaFoDaGB60ga2pRaFC6iTZ02I2jzmH9IalQVlJ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHttpClient = new AsyncHttpClient();

        mBuyButton = (Button) findViewById(R.id.braintreeSubmit);
        // following code initials paypal service...
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BraintreePaymentActivity.class);
//                mClientToken="eyJ2ZXJzaW9uIjoxLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiI0NDA4ODM3MzI1YWU5NzcxYTQxNjdlNjYwNDVkYjZlODEwNjlmYjViZTVhMjA1N2M2MTA4ZDRjNDQyMzkwYmI5fGNyZWF0ZWRfYXQ9MjAxNC0xMi0yNVQwMDoxMDowMy4xMDM1MzYwMTErMDAwMFx1MDAyNm1lcmNoYW50X2lkPWRjcHNweTJicndkanIzcW5cdTAwMjZwdWJsaWNfa2V5PTl3d3J6cWszdnIzdDRuYzgiLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvZGNwc3B5MmJyd2RqcjNxbi9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwicGF5bWVudEFwcHMiOltdLCJjbGllbnRBcGlVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvZGNwc3B5MmJyd2RqcjNxbi9jbGllbnRfYXBpIiwiYXNzZXRzVXJsIjoiaHR0cHM6Ly9hc3NldHMuYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhdXRoVXJsIjoiaHR0cHM6Ly9hdXRoLnZlbm1vLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb20iLCJhbmFseXRpY3MiOnsidXJsIjoiaHR0cHM6Ly9jbGllbnQtYW5hbHl0aWNzLnNhbmRib3guYnJhaW50cmVlZ2F0ZXdheS5jb20ifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6ZmFsc2UsInBheXBhbEVuYWJsZWQiOnRydWUsInBheXBhbCI6eyJkaXNwbGF5TmFtZSI6IkFjbWUgV2lkZ2V0cywgTHRkLiAoU2FuZGJveCkiLCJjbGllbnRJZCI6bnVsbCwicHJpdmFjeVVybCI6Imh0dHA6Ly9leGFtcGxlLmNvbS9wcCIsInVzZXJBZ3JlZW1lbnRVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vdG9zIiwiYmFzZVVybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXNzZXRzVXJsIjoiaHR0cHM6Ly9jaGVja291dC5wYXlwYWwuY29tIiwiZGlyZWN0QmFzZVVybCI6bnVsbCwiYWxsb3dIdHRwIjp0cnVlLCJlbnZpcm9ubWVudE5vTmV0d29yayI6dHJ1ZSwiZW52aXJvbm1lbnQiOiJvZmZsaW5lIiwibWVyY2hhbnRBY2NvdW50SWQiOiJzdGNoMm5mZGZ3c3p5dHc1IiwiY3VycmVuY3lJc29Db2RlIjoiVVNEIn0sImNvaW5iYXNlRW5hYmxlZCI6ZmFsc2V9";
                intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, mClientToken);

                Customization customization = new Customization.CustomizationBuilder()
                        .primaryDescription("Cart")
                        .secondaryDescription("3 Items")
                        .amount("$35")
                        .submitButtonText("Purchase")
                        .build();
                intent.putExtra(BraintreePaymentActivity.EXTRA_CUSTOMIZATION, customization);


                startActivityForResult(intent,DROP_IN_REQUEST);



            }
        });

        mPP_buynow = (Button) findViewById(R.id.pp_buynow);
        mPP_buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PayPalPayment payment = new PayPalPayment(new BigDecimal("10"),"USD","Moto360",PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
                startActivityForResult(intent,PAYPAL_REQUEST);

        }
    });


    if(myapplication.isNetWorkConnected(this)){

        mBuyButton.setEnabled(false);
        getClientToken();
        Toast.makeText(getApplicationContext(),"active Network",Toast.LENGTH_LONG).show();
    }
    else{
        mBuyButton.setEnabled(false);
        Toast.makeText(getApplicationContext(), "no Network",Toast.LENGTH_LONG).show();
        Log.d("NetWork","no network");
    }
    TextView serverURL = (TextView) findViewById(R.id.serverURL);
    serverURL.setText("tokenURL is "+Myapplication.getClientTokenUrl(this)+ mClientToken);
}

    private void getClientToken() {
        mHttpClient.get(Myapplication.getClientTokenUrl(this), new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    mClientToken = new JSONObject(responseString).getString("client_token");
                    mBuyButton.setEnabled(true);
                    Log.d("Token",mClientToken);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, "Unable to decode client token",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Unable to get a client token. Status code:Error:" +
                        responseString,Toast.LENGTH_LONG);
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DROP_IN_REQUEST && resultCode == Activity.RESULT_OK) {
            switch (resultCode) {
                case BraintreePaymentActivity.RESULT_OK:
                    String paymentMethodNonce = data
                            .getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);
                    break;
                case BraintreePaymentActivity.BRAINTREE_RESULT_DEVELOPER_ERROR:
                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_ERROR:
                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_UNAVAILABLE:
                    // handle errors here, a throwable may be available in
                    // data.getSerializableExtra(BraintreePaymentActivity.EXTRA_ERROR_MESSAGE)
                    break;


                default:
                    break;
            }
        }else if(requestCode == PAYPAL_REQUEST && resultCode == Activity.RESULT_OK){

            PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("paymentExample", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                }
            }
        }
        else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("paymentExample", "The user canceled.");
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
