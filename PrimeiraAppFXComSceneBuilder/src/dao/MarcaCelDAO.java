package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import modelo.*;


public class MarcaCelDAO {
	
	public Conexao conexao = new Conexao();
	   public int incluiTipoBanco(MarcaCel c) throws SQLException
	    {
	        //retorna 0=> erro na inclusÃ£o
	        //        1=> incluiu
	        //        2=> registro cadastrado
	        int retorno=0;
	        c.setIn_pv_ret(retorno);
	        String sql=null;
	        
	        Connection conn= conexao.abreConexaoBD();
	        ResultSet rs;
	        Statement st = conn.createStatement();
	        String desc=c.getSt_pv_marca().trim();
	        String cpf=c.getSt_pv_modelo().trim();
	        sql="select * from marcacel where marca ='"+desc+"' or modelo ='"+cpf+"'";
	        rs = st.executeQuery(sql);
	        if (rs.next()==false) {
	                    retorno=0;
	                    c.setIn_pv_ret(retorno);
	       // c.setMsg("Erro de Inclusão");
	        }     else		{
	        	            retorno=2; // possui cadastro
	        	            c.setSt_pv_msg("possui cadastro msg");
	        	            c.setIn_pv_ret(retorno);
	        }
	        	        if (retorno==0)		
	        	        {		
	        	            sql="insert into marcacel (marca,modelo,cor) values (";		
	        	            sql+="'"+c.getSt_pv_marca()+"','"+c.getSt_pv_modelo()+"','"+c.getSt_pv_cor()+" ')";		
	        	            int qRs= st.executeUpdate(sql);		
	        	            if (qRs==0) {		
	        	                retorno=0;	
	        	                c.setSt_pv_msg("Erro de Inclusão");
	        	                c.setIn_pv_ret(retorno);
	        	            }    else {		
	        	                retorno=1;
	        	                c.setSt_pv_msg("Inclusão realizada com sucesso");
	        	                c.setIn_pv_ret(retorno);
	        	            }
	        	        }		
	        	        return  c.getIn_pv_ret();
	    }
    
   public MarcaCel buscaTipoBancoPorId(int codigo )
         throws SQLException {
	   MarcaCel a = new MarcaCel();
	   String sql=null;
	sql="select * from marcacel where codigo = "+codigo;
	Connection conn= conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st=null;
	st = conn.createStatement();
	rs= st.executeQuery(sql);
	if (rs.next()==true)
	{

	    a.setIn_pv_codigo(rs.getInt("codigo"));
	    a.setSt_pv_marca(rs.getString("marca"));
	    a.setSt_pv_modelo(rs.getString("modelo"));
	    a.setSt_pv_cor(rs.getString("cor"));
	    
	    }
	else {
		if(rs.next() == false) {
			JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
		}
//	   a=null;

	}
	   return a;
    }
    


   
   public int excluirTipoID(MarcaCel c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       String desc=c.getSt_pv_marca().trim();
      // System.out.println(c.getTipoBanco_id());
         sql="select * from marcacel where marca = "+"'"+c.getSt_pv_marca()+"'"+" and codigo = "+c.getIn_pv_codigo();
       
       rs = st.executeQuery(sql);
       if (rs.next()==false) {
                   retorno=0;
                   c.setIn_pv_ret(retorno);
           	//c.setMsg("Erro ao excluir");
       	}   
     /*  else		{
	            retorno=2; // possui cadastro
	            c.setMsg("dados não encotrados");
	            c.setRet(retorno);
       		}  */
	        if (retorno==0)		
	        {		
	        	sql="delete from marcacel where codigo = "+c.getIn_pv_codigo();	         
	        	int qR= st.executeUpdate(sql);		
	            if (qR==0) {		
	                retorno=0;	
	                c.setSt_pv_msg("Erro ao excluir");
	                c.setIn_pv_ret(retorno);
	            }    else {		
	                retorno=1;
	                c.setSt_pv_msg("Excluido registro");
	                c.setIn_pv_ret(retorno);
	            }
	        }		
	        return  c.getIn_pv_ret();
   }
   
   
   
   
   public int alterar(MarcaCel c) throws SQLException
   {
       //retorna 0=> erro na inclusÃ£o
       //        1=> incluiu
       //        2=> registro cadastrado
       int retorno=0;
       String sql=null;
       Connection conn= conexao.abreConexaoBD();
       ResultSet rs;
       Statement st = conn.createStatement();
       sql="select * from marcacel where marca = "+"'"+c.getSt_pv_marca()+"'"+" and codigo = "+c.getIn_pv_codigo()+" and modelo = "
    		   +"'"+c.getSt_pv_modelo()+"'"+" and cor = "+"'"+c.getSt_pv_cor()+"'";
       
       rs = st.executeQuery(sql);
       if (rs.next()==false) {
                   retorno=0;
                   c.setIn_pv_ret(retorno);
           //	c.setMsg("ERRO UPDATE");
       	}     else		{
	            retorno=2; // possui cadastro
	            c.setSt_pv_msg("Mesmo dados informados");
	            c.setIn_pv_ret(retorno);
       		}
	        if (retorno==0)		
	        {		
	        	 sql="update marcacel set marca = "+"'"+c.getSt_pv_marca()+"'"+", modelo= "+"'"+c.getSt_pv_modelo()+"'"+", cor ="
	        	 		+ "'"+c.getSt_pv_cor()+"'"+" where codigo = "+c.getIn_pv_codigo();
	            int qR= st.executeUpdate(sql);		
	            if (qR==0) {		
	                retorno=0;	
	                c.setSt_pv_msg("ERRO UPDATE");
	                c.setIn_pv_ret(retorno);
	            }    else {		
	                retorno=1;
	                c.setSt_pv_msg("Alterado registro");
	                c.setIn_pv_ret(retorno);
	            }
	        }		
	        return  c.getIn_pv_ret();
   }
   
   
   
   
   
   public List<Cliente> listar() {
       String sql = "SELECT * FROM cliente";
       List<Cliente> retorno = new ArrayList<>();
       try {
          // PreparedStatement stmt = connection.prepareStatement(sql);
          // ResultSet resultado = stmt.executeQuery();
           
           Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
               Cliente a = new Cliente();
              // TipoBanco.setTipoBanco_id(rs.getInt("cdTipoBanco"));
               a.setSt_pv_nome(rs.getString("nome"));
               a.setSt_pv_cpf(rs.getString("cpf"));
               a.setIn_pv_telefone(rs.getInt("telefone"));
               a.setIn_pv_codigo(rs.getInt("codigo"));
               
               retorno.add(a);
               int b=0 ;
        
               
           }
           
           for (Cliente cliente : retorno) {
   			System.out.println(cliente.getSt_pv_nome());
          }

           
          // ;
       } catch (SQLException ex) {
  //         Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;

   }

   
   
   
   
   public List<TipoBanco> teste() {
       String sql = "SELECT * FROM tipos_bd";
       List<TipoBanco> retorno = new ArrayList<>();
       try {
    	   Connection conn= conexao.abreConexaoBD();
           ResultSet rs = null;
           Statement st=null;
           st = conn.createStatement();
           rs= st.executeQuery(sql);
           while (rs.next()) {
        	   TipoBanco a = new TipoBanco();
        	   a.setTipoBanco_sigla(rs.getString("tip_sigla"));
               retorno.add(a);
           }
       } catch (SQLException ex) {
           Logger.getLogger(TipoBancoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       return retorno;
   }
   
   
   }


