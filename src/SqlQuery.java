public class SqlQuery {
    public static void main(String[] args) {
        String[] branch_name = {"서울지점", "성남지점", "광주지점", "대구지점", "부산지점", "대전지점", "제주지점"};
        String[] branch_head = {"강동희", "박찬주", "김기백", "김기식", "홍상순", "이연희", "고희경"};
        String[] address = {"서울", "성남", "광주", "대구", "부산", "대전", "제주"};

        for (int i = 0; i < branch_name.length; i++) {
            System.out.println("insert into branch values('" + branch_name[i] + "', '" + branch_head[i] + "', '" + address[i] + "');");
        }

        String[] deposit_num = new String("100 101 102 103 104 105 106 107 108 109 110 111").split(" ");
        String[] ssn = new String("970102-1234123\n" +
                "870423-2312593\n" +
                "890902-1248311\n" +
                "890302-1273121\n" +
                "900402-1235721\n" +
                "951012-1234123\n" +
                "970102-1234123\n" +
                "910502-2123121\n" +
                "890302-1273121\n" +
                "910931-2109211\n" +
                "890302-1273121\n" +
                "970306-1298101").split("\n");

        String[] balance = new String("330000\n" +
                "120000\n" +
                "2300000\n" +
                "560000\n" +
                "870000\n" +
                "9000\n" +
                "110000\n" +
                "1900000\n" +
                "320000\n" +
                "560000\n" +
                "1200000\n" +
                "900000").split("\n");
        branch_name = new String("서울지점\n" +
                "대전지점\n" +
                "성남지점\n" +
                "광주지점\n" +
                "성남지점\n" +
                "대구지점\n" +
                "대구지점\n" +
                "서울지점\n" +
                "광주지점\n" +
                "성남지점\n" +
                "서울지점\n" +
                "제주지점").split("\n");
        for (int i = 0; i < deposit_num.length; i++) {
            System.out.println("insert into deposit values('" + deposit_num[i] + "', '" + ssn[i] + "', '" + balance[i] + "', '" + branch_name[i] + "');");
        }

        ssn = new String("970102-1234123\n" +
                "890302-1273121\n" +
                "870423-2312593\n" +
                "910502-2123121\n" +
                "970306-1298101\n" +
                "910931-2109211\n" +
                "890902-1248311\n" +
                "900402-1235721\n" +
                "910328-2212123\n" +
                "951012-1234123").split("\n");

        String[] name = new String("김기식\n" +
                "홍순태\n" +
                "강지선\n" +
                "황현희\n" +
                "정성태\n" +
                "박선희\n" +
                "이기상\n" +
                "박지성\n" +
                "이영순\n" +
                "차두리").split("\n");

        address = new String("서울\n" +
                "서울\n" +
                "부산\n" +
                "부산\n" +
                "대구\n" +
                "서울\n" +
                "대전\n" +
                "서울\n" +
                "부산\n" +
                "제주").split("\n");

        String[] phone = new String("010-2121-1231\n" +
                "010-3242-2352\n" +
                "010-5223-3214\n" +
                "010-5394-0909\n" +
                "010-4392-3241\n" +
                "010-7984-1383\n" +
                "010-5335-9786\n" +
                "010-5910-2312\n" +
                "010-9876-2323\n" +
                "010-5920-2312").split("\n");

        for (int i = 0; i < ssn.length; i++) {
            System.out.println("insert into client values('" + ssn[i] + "', '" + name[i] + "', '" + address[i] + "', '" + phone[i] + "');");
        }

        String[] emp_id = new String("100\n" +
                "101\n" +
                "102\n" +
                "103\n" +
                "104\n" +
                "105").split("\n");

        name = new String("김경호\n" +
                "자우림\n" +
                "김범수\n" +
                "윤민수\n" +
                "박정현\n" +
                "박완규").split("\n");


        String[] period_emp = new String("10\n" +
                "12\n" +
                "8\n" +
                "8\n" +
                "9\n" +
                "12").split("\n");


        String[] dept = new String("총무부\n" +
                "관리부\n" +
                "인사부\n" +
                "인사부\n" +
                "총무부\n" +
                "관리부").split("\n");




        for (int i = 0; i < emp_id.length; i++) {
            System.out.println("insert into employee values('" + emp_id[i] + "', '" + name[i] + "', '" + period_emp[i] + "', '" + dept[i] + "');");
        }


        dept = new String("총무부\n" +
                "관리부\n" +
                "인사부").split("\n");

        phone = new String("02-201-2343\n" +
                "02-201-4367\n" +
                "02-201-0932").split("\n");


        String[] office = new String("301호\n" +
                "103호\n" +
                "201호").split("\n");



        for (int i = 0; i < dept.length; i++) {
            System.out.println("insert into department values('" + dept[i] + "', '" + phone[i] + "', '" + office[i] + "');");
        }

        String[] project_name = new String("A\n" +
                "B\n" +
                "C").split("\n");


        address = new String("서울\n" +
                "부산\n" +
                "대전").split("\n");

        String[] period = new String("2\n" +
                "4\n" +
                "1").split("\n");




        for (int i = 0; i < project_name.length; i++) {
            System.out.println("insert into project values('" + project_name[i] + "', '" + address[i] + "', '" + period[i] + "');");
        }


        emp_id = new String("100\n" +
                "100\n" +
                "101\n" +
                "103\n" +
                "104\n" +
                "104").split("\n");
        project_name = new String("A\n" +
                "C\n" +
                "A\n" +
                "B\n" +
                "B\n" +
                "A\n" +
                "A").split("\n");
        String[] strings = new String("").split("\n");

        for (int i = 0; i < emp_id.length; i++) {
            System.out.println("insert into assign values('" + emp_id[i] + "', '" + project_name[i] + "');");
        }

    }
}
