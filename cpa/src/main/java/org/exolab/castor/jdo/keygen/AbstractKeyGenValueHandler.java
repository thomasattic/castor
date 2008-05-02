package org.exolab.castor.jdo.keygen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.castor.util.Messages;
import org.exolab.castor.jdo.PersistenceException;
import org.exolab.castor.persist.spi.KeyGenerator;

public abstract class AbstractKeyGenValueHandler {
    private KeyGenerator _keyGenerator;
    private IdentityValue _identityValue;

    AbstractKeyGenValueHandler() { }
    
    public Object getValue(final PreparedStatement stmt)
    throws PersistenceException, SQLException {
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { return _identityValue.getValue(rs); }
        String msg = Messages.format("persist.keyGenFailed", _keyGenerator.getClass().getName());
        throw new PersistenceException(msg);
    }

    public Object getValue(final String sql, final Connection conn)
    throws PersistenceException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            return getValue(stmt);
        } catch (SQLException e) {
            String msg = Messages.format("persist.keyGenSQL", 
                    _keyGenerator.getClass().getName(), e.toString());
            throw new PersistenceException(msg);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void setGenerator(final KeyGenerator generator) {
        _keyGenerator = generator;
    }

    public void setIdentityValue(final IdentityValue identityValue) {
        _identityValue = identityValue;
    }
}


