//package net.skhu.search;
//
//
//public List<UserVO> findUser(String type, String keyword)
//        throws SQLException{
//        try {
//        String colName="";
//        switch(type) {
//        case "1": colName="name";
//        break;
//        case "2": colName="userid";
//        break;
//        case "3": colName="hp1||hp2||hp3";
//        break;
//        }
//
//        con = DBUtil.getCon();
//
//        String sql="select * from member where "+colName+" like ?";
//        System.out.println(sql);
//        ps=con.prepareStatement(sql);
//        ps.setString(1, "%"+keyword+"%");
//        rs=ps.executeQuery();
//
//        return makeList(rs);
//
//
//        }finally {
//        close();
//        }
//        }