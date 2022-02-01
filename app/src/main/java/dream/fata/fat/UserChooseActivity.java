package dream.fata.fat;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.Random;

public class UserChooseActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private Toolbar _toolbar;
	private Button button_patti;
	private Button button_single;
	private Intent i = new Intent();
	private LinearLayout linear22;
	private LinearLayout linear23;
	private LinearLayout linear24;
	private LinearLayout linear25;
	private TextView textview5;

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.user_choose);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		this._app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		this._coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		Toolbar toolbar = (Toolbar) findViewById(R.id._toolbar);
		this._toolbar = toolbar;
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		this._toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserChooseActivity.AnonymousClass1 */

			public void onClick(View _v) {
				UserChooseActivity.this.onBackPressed();
			}
		});
		this.linear22 = (LinearLayout) findViewById(R.id.linear22);
		this.linear23 = (LinearLayout) findViewById(R.id.linear23);
		this.textview5 = (TextView) findViewById(R.id.textview5);
		this.linear24 = (LinearLayout) findViewById(R.id.linear24);
		this.linear25 = (LinearLayout) findViewById(R.id.linear25);
		this.button_single = (Button) findViewById(R.id.button_single);
		this.button_patti = (Button) findViewById(R.id.button_patti);
		this.button_single.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserChooseActivity.AnonymousClass2 */

			public void onClick(View _view) {
				if (UserChooseActivity.this.getIntent().getStringExtra("Game").contains("_r")) {
					UserChooseActivity.this.i.setClass(UserChooseActivity.this.getApplicationContext(), ResultActivity.class);
					UserChooseActivity.this.i.putExtra("Game", UserChooseActivity.this.getIntent().getStringExtra("Game"));
					UserChooseActivity.this.i.putExtra("Game_sp", "single");
					UserChooseActivity userChooseActivity = UserChooseActivity.this;
					userChooseActivity.startActivity(userChooseActivity.i);
					return;
				}
				UserChooseActivity.this.i.setClass(UserChooseActivity.this.getApplicationContext(), PlaySingleActivity.class);
				UserChooseActivity.this.i.putExtra("Game", UserChooseActivity.this.getIntent().getStringExtra("Game"));
				UserChooseActivity userChooseActivity2 = UserChooseActivity.this;
				userChooseActivity2.startActivity(userChooseActivity2.i);
			}
		});
		this.button_patti.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserChooseActivity.AnonymousClass3 */

			public void onClick(View _view) {
				if (UserChooseActivity.this.getIntent().getStringExtra("Game").contains("_r")) {
					UserChooseActivity.this.i.setClass(UserChooseActivity.this.getApplicationContext(), ResultActivity.class);
					UserChooseActivity.this.i.putExtra("Game", UserChooseActivity.this.getIntent().getStringExtra("Game"));
					UserChooseActivity.this.i.putExtra("Game_sp", "patti");
					UserChooseActivity userChooseActivity = UserChooseActivity.this;
					userChooseActivity.startActivity(userChooseActivity.i);
					return;
				}
				UserChooseActivity.this.i.setClass(UserChooseActivity.this.getApplicationContext(), PlayPattiActivity.class);
				UserChooseActivity.this.i.putExtra("Game", UserChooseActivity.this.getIntent().getStringExtra("Game"));
				UserChooseActivity userChooseActivity2 = UserChooseActivity.this;
				userChooseActivity2.startActivity(userChooseActivity2.i);
			}
		});
	}

	private void initializeLogic() {
		this.button_single.setElevation(30.0f);
		this.button_single.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserChooseActivity.AnonymousClass4 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 0, -1, -28928));
		this.button_patti.setElevation(30.0f);
		this.button_patti.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserChooseActivity.AnonymousClass5 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 0, -1, -1499549));
		this.textview5.setText("Choose Your Preferred ".concat(getIntent().getStringExtra("Game").concat(" Game")));
	}

	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	@Deprecated
	public int getLocationX(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}

	@Deprecated
	public int getLocationY(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}

	@Deprecated
	public int getRandom(int _min, int _max) {
		return new Random().nextInt((_max - _min) + 1) + _min;
	}

	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx)) {
				_result.add(Double.valueOf((double) _arr.keyAt(_iIdx)));
			}
		}
		return _result;
	}

	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(1, (float) _input, getResources().getDisplayMetrics());
	}

	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
