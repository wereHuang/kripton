package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Contact {

	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@BindSqlAdapter(adapter = DateAdapterType.class, dataType = Long.class)
	public Date birthDay;
		
	@BindSqlAdapter(adapter = PasswordAdapterType.class, dataType = byte[].class)
	protected String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
//	@BindSqlAdapter(adapter = AdapterType.class, dataType = Long.class)
//	public byte[] password;
}