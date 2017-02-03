package exemplo.util;

import java.sql.Connection;
import java.sql.Date;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConexaoUtil {

	private Connection conexao;

	public ConexaoUtil() {
		conexao = abreConexao();
	}

	private Connection abreConexao() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/bdexemplo");
			return ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possivel abrir a conexao", e);
		}
	}

	public Connection getConexao() {
		return this.conexao;
	}

	public void fechaConexao() {
		try {
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Date toSqlDate(java.util.Date dt) {
		if (dt == null) 
			return null;
		return new Date(dt.getTime());
	}
}
