package dream.fata.fat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class GameRulesActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private HashMap<String, Object> rule = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> childkey = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	private TextView textview1;
	private ProgressBar progressbar1;
	
	private DatabaseReference game_rules = _firebase.getReference("game_rules");
	private ChildEventListener _game_rules_child_listener;
	private SharedPreferences admin;
	private AlertDialog.Builder d;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.game_rules);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		listview1 = findViewById(R.id.listview1);
		textview1 = findViewById(R.id.textview1);
		progressbar1 = findViewById(R.id.progressbar1);
		admin = getSharedPreferences("admin", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		dialog = new AlertDialog.Builder(this);

		this.listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			/* class dream.fata.fat.GameRulesActivity.AnonymousClass2 */

			@Override // android.widget.AdapterView.OnItemLongClickListener
			public boolean onItemLongClick(AdapterView<?> adapterView, View _param2, final int _param3, long _param4) {
				if (!GameRulesActivity.this.admin.getString("admin", "").equals("true")) {
					return true;
				}
				GameRulesActivity.this.d.setTitle("Modify Rules");
				GameRulesActivity.this.d.setMessage("Select any option to continue");
				GameRulesActivity.this.d.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
					/* class dream.fata.fat.GameRulesActivity.AnonymousClass2.AnonymousClass1 */

					public void onClick(DialogInterface _dialog, int _which) {
						GameRulesActivity.this.dialog.setTitle("Change the rule");
						GameRulesActivity.this.dialog.setMessage("Enter Your New Rule Here");
						final EditText edittext1 = new EditText(GameRulesActivity.this);
						edittext1.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
						GameRulesActivity.this.dialog.setView(edittext1);
						GameRulesActivity.this.dialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
							/* class dream.fata.fat.GameRulesActivity.AnonymousClass2.AnonymousClass1.AnonymousClass1 */

							public void onClick(DialogInterface _dialog, int _which) {
								GameRulesActivity.this.rule = new HashMap();
								GameRulesActivity.this.rule.put("rule", edittext1.getText().toString());
								GameRulesActivity.this.game_rules.child((String) GameRulesActivity.this.childkey.get(_param3)).updateChildren(GameRulesActivity.this.rule);
								((HashMap) GameRulesActivity.this.listmap.get(_param3)).put("rule", edittext1.getText().toString());
								GameRulesActivity.this.listview1.setAdapter((ListAdapter) new Listview1Adapter(GameRulesActivity.this.listmap));
								((BaseAdapter) GameRulesActivity.this.listview1.getAdapter()).notifyDataSetChanged();
							}
						});
						GameRulesActivity.this.dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							/* class dream.fata.fat.GameRulesActivity.AnonymousClass2.AnonymousClass1.AnonymousClass2 */

							public void onClick(DialogInterface _dialog, int _which) {
							}
						});
						GameRulesActivity.this.dialog.create().show();
					}
				});
				GameRulesActivity.this.d.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					/* class dream.fata.fat.GameRulesActivity.AnonymousClass2.AnonymousClass2 */

					public void onClick(DialogInterface _dialog, int _which) {
						GameRulesActivity.this.game_rules.child((String) GameRulesActivity.this.childkey.get(_param3)).removeValue();
						GameRulesActivity.this.listmap.remove(_param3);
						GameRulesActivity.this.childkey.remove(_param3);
						GameRulesActivity.this.listview1.setAdapter((ListAdapter) new Listview1Adapter(GameRulesActivity.this.listmap));
						((BaseAdapter) GameRulesActivity.this.listview1.getAdapter()).notifyDataSetChanged();
					}
				});
				GameRulesActivity.this.d.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
					/* class dream.fata.fat.GameRulesActivity.AnonymousClass2.AnonymousClass3 */

					public void onClick(DialogInterface _dialog, int _which) {
					}
				});
				GameRulesActivity.this.d.create().show();
				return true;
			}
		});
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				rule = new HashMap<>();
				rule.put("rule", "Click and hold to edit");
				game_rules.push().updateChildren(rule);
			}
		});
		
		_game_rules_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				childkey.add(_childKey);
				game_rules.addListenerForSingleValueEvent(new ValueEventListener() {
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
						linear2.setVisibility(View.GONE);
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
		game_rules.addChildEventListener(_game_rules_child_listener);
	}
	
	private void initializeLogic() {
		if (admin.getString("admin", "").equals("true")) {
			_fab.show();
		}
		else {
			_fab.hide();
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.cus_game_rules, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			
			textview1.setText(_data.get((int)_position).get("rule").toString());
			linear1.setElevation((float)30);
			linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFFCFD8DC, 0xFFFFFFFF));
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
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
