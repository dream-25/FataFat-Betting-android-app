package dream.fata.fat;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayHistoryActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Toolbar _toolbar;
	private ChildEventListener _user_logGames_child_listener;
	private Button button_fatafat;
	private Button button_goa;
	private Button button_main;
	private Button button_patti;
	private Button button_single;
	private String game = "";
	private String game_sp = "";
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ListView listview1;
	private String path_user_logGames = "";
	private ProgressDialog pd;
	private TextView textview1;
	private DatabaseReference user_logGames = this._firebase.getReference("user_logGames");

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.play_history);
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
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass1 */

			public void onClick(View _v) {
				PlayHistoryActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.listview1 = (ListView) findViewById(R.id.listview1);
		this.button_single = (Button) findViewById(R.id.button_single);
		this.button_patti = (Button) findViewById(R.id.button_patti);
		this.button_fatafat = (Button) findViewById(R.id.button_fatafat);
		this.button_goa = (Button) findViewById(R.id.button_goa);
		this.button_main = (Button) findViewById(R.id.button_main);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.button_single.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass2 */

			public void onClick(View _view) {
				PlayHistoryActivity.this.pd.show();
				PlayHistoryActivity.this.game_sp = "Single";
				PlayHistoryActivity.this.textview1.setText("Viewing Result Of : ".concat(PlayHistoryActivity.this.game.concat(" ".concat(PlayHistoryActivity.this.game_sp))));
				PlayHistoryActivity playHistoryActivity = PlayHistoryActivity.this;
				playHistoryActivity.path_user_logGames = "userHistory/".concat(playHistoryActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlayHistoryActivity.this.game.concat("_".concat(PlayHistoryActivity.this.game_sp)))));
				PlayHistoryActivity.this.user_logGames.removeEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity playHistoryActivity2 = PlayHistoryActivity.this;
				playHistoryActivity2.user_logGames = playHistoryActivity2._firebase.getReference(PlayHistoryActivity.this.path_user_logGames);
				PlayHistoryActivity.this.user_logGames.addChildEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity.this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						pd.dismiss();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		this.button_patti.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass3 */

			public void onClick(View _view) {
				PlayHistoryActivity.this.pd.show();
				PlayHistoryActivity.this.game_sp = "Patti";
				PlayHistoryActivity.this.textview1.setText("Viewing Result Of : ".concat(PlayHistoryActivity.this.game.concat(" ".concat(PlayHistoryActivity.this.game_sp))));
				PlayHistoryActivity playHistoryActivity = PlayHistoryActivity.this;
				playHistoryActivity.path_user_logGames = "userHistory/".concat(playHistoryActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlayHistoryActivity.this.game.concat("_".concat(PlayHistoryActivity.this.game_sp)))));
				PlayHistoryActivity.this.user_logGames.removeEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity playHistoryActivity2 = PlayHistoryActivity.this;
				playHistoryActivity2.user_logGames = playHistoryActivity2._firebase.getReference(PlayHistoryActivity.this.path_user_logGames);
				PlayHistoryActivity.this.user_logGames.addChildEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity.this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						pd.dismiss();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		this.button_fatafat.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass4 */

			public void onClick(View _view) {
				PlayHistoryActivity.this.pd.show();
				PlayHistoryActivity.this.game = "Fatafat";
				PlayHistoryActivity.this.textview1.setText("Viewing Result Of : ".concat(PlayHistoryActivity.this.game.concat(" ".concat(PlayHistoryActivity.this.game_sp))));
				PlayHistoryActivity playHistoryActivity = PlayHistoryActivity.this;
				playHistoryActivity.path_user_logGames = "userHistory/".concat(playHistoryActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlayHistoryActivity.this.game.concat("_".concat(PlayHistoryActivity.this.game_sp)))));
				PlayHistoryActivity.this.user_logGames.removeEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity playHistoryActivity2 = PlayHistoryActivity.this;
				playHistoryActivity2.user_logGames = playHistoryActivity2._firebase.getReference(PlayHistoryActivity.this.path_user_logGames);
				PlayHistoryActivity.this.user_logGames.addChildEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity.this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						pd.dismiss();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		this.button_goa.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass5 */

			public void onClick(View _view) {
				PlayHistoryActivity.this.pd.show();
				PlayHistoryActivity.this.game = "SILIGURI";
				PlayHistoryActivity.this.textview1.setText("Viewing Result Of : ".concat(PlayHistoryActivity.this.game.concat(" ".concat(PlayHistoryActivity.this.game_sp))));
				PlayHistoryActivity playHistoryActivity = PlayHistoryActivity.this;
				playHistoryActivity.path_user_logGames = "userHistory/".concat(playHistoryActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlayHistoryActivity.this.game.concat("_".concat(PlayHistoryActivity.this.game_sp)))));
				PlayHistoryActivity.this.user_logGames.removeEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity playHistoryActivity2 = PlayHistoryActivity.this;
				playHistoryActivity2.user_logGames = playHistoryActivity2._firebase.getReference(PlayHistoryActivity.this.path_user_logGames);
				PlayHistoryActivity.this.user_logGames.addChildEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity.this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						pd.dismiss();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		this.button_main.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlayHistoryActivity.AnonymousClass6 */

			public void onClick(View _view) {
				PlayHistoryActivity.this.pd.show();
				PlayHistoryActivity.this.game = "Main Bazar";
				PlayHistoryActivity.this.textview1.setText("Viewing Result Of : ".concat(PlayHistoryActivity.this.game.concat(" ".concat(PlayHistoryActivity.this.game_sp))));
				PlayHistoryActivity playHistoryActivity = PlayHistoryActivity.this;
				playHistoryActivity.path_user_logGames = "userHistory/".concat(playHistoryActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlayHistoryActivity.this.game.concat("_".concat(PlayHistoryActivity.this.game_sp)))));
				PlayHistoryActivity.this.user_logGames.removeEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity playHistoryActivity2 = PlayHistoryActivity.this;
				playHistoryActivity2.user_logGames = playHistoryActivity2._firebase.getReference(PlayHistoryActivity.this.path_user_logGames);
				PlayHistoryActivity.this.user_logGames.addChildEventListener(PlayHistoryActivity.this._user_logGames_child_listener);
				PlayHistoryActivity.this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						pd.dismiss();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
		});
		_user_logGames_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(listmap));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}

			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

			}

			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {

			}

			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);

			}

			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();

			}
		};
		user_logGames.addChildEventListener(_user_logGames_child_listener);
	}


	private void initializeLogic() {
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setCancelable(false);
		this.pd.show();
		this.game = "Fatafat";
		this.game_sp = "Single";
		this.textview1.setText("Viewing Result Of : ".concat("Fatafat".concat(" ".concat("Single"))));
		this.path_user_logGames = "userHistory/".concat(getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(this.game.concat("_".concat(this.game_sp)))));
		this.user_logGames.removeEventListener(this._user_logGames_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path_user_logGames);
		this.user_logGames = reference;
		reference.addChildEventListener(this._user_logGames_child_listener);
		this.listview1.setStackFromBottom(true);
		this.user_logGames.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				listmap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						listmap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				listview1.setAdapter(new Listview1Adapter(listmap));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				pd.dismiss();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
	}

	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;

		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			this._data = _arr;
		}

		public int getCount() {
			return this._data.size();
		}

		public HashMap<String, Object> getItem(int _index) {
			return this._data.get(_index);
		}

		public long getItemId(int _index) {
			return (long) _index;
		}

		public View getView(int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) PlayHistoryActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.games, (ViewGroup) null);
			}
			LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			TextView textview4 = (TextView) _view.findViewById(R.id.textview4);
			TextView textview5 = (TextView) _view.findViewById(R.id.textview5);
			if (((HashMap) PlayHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Bet Added")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.PlayHistoryActivity.Listview1Adapter.AnonymousClass1 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, ViewCompat.MEASURED_SIZE_MASK));
			} else if (((HashMap) PlayHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Won")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.PlayHistoryActivity.Listview1Adapter.AnonymousClass2 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -10044566));
			} else if (((HashMap) PlayHistoryActivity.this.listmap.get(_position)).get(NotificationCompat.CATEGORY_STATUS).toString().equals("Loss")) {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.PlayHistoryActivity.Listview1Adapter.AnonymousClass3 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, -769226));
			} else {
				linear1.setElevation(30.0f);
				linear1.setBackground(new GradientDrawable() {
					/* class dream.fata.fat.PlayHistoryActivity.Listview1Adapter.AnonymousClass4 */

					public GradientDrawable getIns(int a, int b, int c, int d) {
						setCornerRadius((float) a);
						setStroke(b, c);
						setColor(d);
						return this;
					}
				}.getIns(20, 1, -14273992, ViewCompat.MEASURED_SIZE_MASK));
			}
			textview1.setText("Date : \n".concat(this._data.get(_position).get("time").toString()));
			textview2.setText("Bazi : \n".concat(this._data.get(_position).get("bazi").toString()));
			textview3.setText("Digit : \n".concat(this._data.get(_position).get("digit").toString()));
			textview4.setText("Rs : \n".concat(this._data.get(_position).get("rs").toString()));
			textview5.setText(this._data.get(_position).get(NotificationCompat.CATEGORY_STATUS).toString());
			return _view;
		}
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
