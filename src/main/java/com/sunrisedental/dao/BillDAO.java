package com.sunrisedental.dao;
import com.sunrisedental.model.Bill;
import com.sunrisedental.util.DBConnection;
import java.math.BigDecimal;
import java.sql.*;

public class BillDAO {
    public int create(Bill b)throws SQLException{
        String s="INSERT INTO bills(bill_number,appointment_id,consultation_fee,treatment_fee,total_amount,payment_status,payment_method) VALUES(?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection();
                PreparedStatement ps=c.prepareStatement(s,Statement.RETURN_GENERATED_KEYS)){ps.setString(1,b.getBillNumber());
                ps.setInt(2,b.getAppointmentId());
                ps.setBigDecimal(3,b.getConsultationFee());
                ps.setBigDecimal(4,b.getTreatmentFee());
                ps.setBigDecimal(5,b.getTotalAmount());
                ps.setString(6,b.getPaymentStatus());
                ps.setString(7,b.getPaymentMethod());
                ps.executeUpdate();
        try(ResultSet k=ps.getGeneratedKeys()){if(k.next()){b.setBillId(k.getInt(1));return b.getBillId();
        }
        }
        }return 0;
    }
    public Bill findByAppointmentId(int appointmentId)throws SQLException{String s="SELECT * FROM bills WHERE appointment_id=?";
    try(Connection c=DBConnection.getConnection();
            PreparedStatement ps=c.prepareStatement(s)){ps.setInt(1,appointmentId);
            try(ResultSet r=ps.executeQuery()){if(!r.next())return null;
            Bill b=new Bill();b.setBillId(r.getInt("bill_id"));
            b.setBillNumber(r.getString("bill_number"));
            b.setAppointmentId(appointmentId);
            b.setConsultationFee(r.getBigDecimal("consultation_fee"));
            b.setTreatmentFee(r.getBigDecimal("treatment_fee"));
            b.setTotalAmount(r.getBigDecimal("total_amount"));
            b.setPaymentStatus(r.getString("payment_status"));
            b.setPaymentMethod(r.getString("payment_method"));
            return b;
            }
    }
    }
    public BigDecimal getTodayRevenue()
        throws SQLException {

    String sql =
            "SELECT COALESCE(SUM(total_amount), 0) "
            + "FROM bills "
            + "WHERE DATE(bill_date) = CURRENT_DATE "
            + "AND payment_status = 'PAID'";

    try (Connection connection =
            DBConnection.getConnection();
         PreparedStatement statement =
            connection.prepareStatement(sql);
         ResultSet resultSet =
            statement.executeQuery()) {

        if (resultSet.next()) {

            return resultSet.getBigDecimal(1);
        }
    }

    return BigDecimal.ZERO;
}
}
