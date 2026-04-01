package com.ningboz.mylearnproject.zhoulei.main;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ningboz.mylearnproject.utils.IndexArrayList;
import com.ningboz.mylearnproject.zhoulei.bean.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    // 客商数据
    public static IndexArrayList<Money> moneyList = new IndexArrayList<>(Money.class);
    // 品牌数据
    public static IndexArrayList<Brand> brandList = new IndexArrayList<>(Brand.class);
    // 费用数据
    public static IndexArrayList<User> userList = new IndexArrayList<>(User.class);

    // 银行账户
    private static final String YHZH = "512908904610301";
    // 银行存款辅助
    private static final String YHCKFZ = "J0101";
    // 预收账款类型
    private static final String YSZKLX = "99";
    // 到期年度
    private static final String DQND = "2025";
    // 科目编码
    private static final String KMBM = "1002";

    // 导入须知
    private static final String DRXZ = "*导入须知:\n" +
            "1.表格中不能增、删、改列及固有内容\n" +
            "2.所有内容必须为文本格式;表格中有多个档案名称字段是为了实现多语,如果没有多语只录第一个名称字段即可\n" +
            "3.枚举项输入错误时，则按默认值处理;勾选框的导入需输入N、Y\n" +
            "4.导入带有子表的档案时,表格中主表与子表之间必须有一空行,且主、子表对应数据需加上相同的行号\n" +
            "5.辅助核算字段保存凭证分录的辅助核算信息，格式为“辅助核算值编码：辅助核算项目类型编码” \n" +
            "  例如：002:0004。前面的002表示辅助核算值编码，后面的0004表示辅助核算项目类型编码\n" +
            "  如果想导入总账，需按照上述例子中的格式填好辅助核算值编码和辅助核算类型编码";




    private static final String defaultCell1 = "\"null_$head,main_m_pk_accountingbook,main_m_pk_vouchertype,main_m_num,main_pk_prepared,main_m_prepareddate,m_explanation,m_accsubjcode,m_pk_currtype,m_debitamount,m_localdebitamount,m_creditamount,m_localcreditamount,ass_1,ass_2,ass_3,ass_4,ass_5\"";
    private static final String defaultCell2 = "\"cashflow,m_flag,cashflowcurr,m_money,m_moneymain,m_moneygroup,m_moneyglobal,cashflowinnercorp,cashflowName,cashflowCode\"";

    private static final String[] row1 = {defaultCell1,"* 核算账簿","* 凭证类别编码","* 凭证号","* 制单人编码","* 制单日期","* 摘要","* 科目编码","* 币种","* 原币借方金额","* 本币借方金额","* 原币贷方金额","* 本币贷方金额","辅助核算1","辅助核算2","辅助核算3","辅助核算4","辅助核算5"};
    private static final String[] row2 = {defaultCell2,"方向","分析币种","原币","组织本币","集团本币","全局本币","内部单位","现金流量名称","现金流量编码"};
    private static List<Indata> indataList = new ArrayList<>();


    public static void main(String[] args) {
        String inFilePath = "C:\\Users\\zhangnb\\Desktop\\财务记账模板.xlsx";
        String outFilePath = "C:\\Users\\zhangnb\\Desktop\\test.xlsx";
        exportExcel(inFilePath,outFilePath);
    }

    public static void exportExcel(String inFilePath,String outFilePath){
        // 加载原始数据
        initData(inFilePath);
        // 数据转换
        ResultData resultData = change(indataList);
        // 导出数据
        exportExcel(resultData,outFilePath);
    }

    private static void initData(String inFilePath) {
        // 读取流水信息
        ExcelReader reader1 = ExcelUtil.getReader(inFilePath,0);
        List<List<Object>> rowList1 = reader1.read();
        rowList1.remove(0);
        boolean isDetail = false;
        int indataId = 1;
        Indata curIndata = null;
        for (List<Object> row : rowList1) {
            if(!isDetail){
                // 总
                if(isEmpty(row.get(0)))
                    break;
                curIndata = new Indata(
                            String.valueOf(indataId),(Date) row.get(0),getStr(row.get(1)),getDouble(row.get(2)),new ArrayList<>()
                        );
                indataId++;
                indataList.add(curIndata);
                isDetail=true;
                continue;
            }
            if(isDetail){
                // 明细
                if(!isEmpty(row.get(0))){
                    curIndata = new Indata(
                            String.valueOf(indataId),(Date) row.get(0),getStr(row.get(1)),getDouble(row.get(2)),new ArrayList<>()
                    );
                    indataId++;
                    indataList.add(curIndata);
                    isDetail=true;
                    continue;
                }else{
                    DetailData detailData = new DetailData(getStr(row.get(3)), getStr(row.get(4)), getStr(row.get(5)), getDouble(row.get(6)), true, curIndata);
                    curIndata.getDetailList().add(detailData);
                }
            }
        }


        // 读取基本信息数据
        ExcelReader reader2 = ExcelUtil.getReader(inFilePath,1);
        List<List<Object>> rowList = reader2.read();
        rowList.remove(0);

        // user
        for (List<Object> row : rowList) {
            if(isEmpty(row.get(0)))
                break;
            User user = new User(getStr(row.get(0)), getStr(row.get(1)));
            userList.add(user);
        }

        // money
        for (List<Object> row : rowList) {
            if(isEmpty(row.get(3)))
                break;
            Money money = new Money(
                        getStr(row.get(3)),
                        getStr(row.get(4)),
                        getStr(row.get(5)),
                        getStr(row.get(6)),
                        getDouble(row.get(7)),
                        getStr(row.get(8)),
                        getStr(row.get(9)),
                        getStr(row.get(10)),
                        getStr(row.get(11)).equals("1")
                    );
            moneyList.add(money);
        }

        // brand
        for (List<Object> row : rowList) {
            if(isEmpty(row.get(13)))
                break;
            Brand brand = new Brand(getStr(row.get(13)), getStr(row.get(14)));
            brandList.add(brand);
        }
    }

    private static void exportExcel(ResultData resultData, String outFilePath) {
        ExcelWriter writer = ExcelUtil.getWriter(outFilePath);
        writer.writeRow(Arrays.asList(DRXZ));
        writer.writeRow(Arrays.asList(row1));

        List<List<String>> list1 = new ArrayList<>();
        List<OutData1> data1List = resultData.getData1List();
        for (int i = 0; i < data1List.size(); i++) {
            OutData1 outData1 = data1List.get(i);
            int index = i;
            list1.add(new ArrayList<String>(){{
                add(String.valueOf(index));
                add(outData1.getHesuanZB());
                add(outData1.getPingzhengType());
                add(outData1.getPingzhenghao());
                add(outData1.getEmail());
                add(outData1.getDate());
                add(outData1.getZhaiyao());
                add(outData1.getCode());
                add(outData1.getBizhong());
                add(outData1.getYbJieJinE());
                add(outData1.getBbJieJinE());
                add(outData1.getYbDaiJinE());
                add(outData1.getBbDaiJinE());
                for (String str : outData1.getFuzhuHesuanList()) {
                    add(str);
                }
            }});
        }
        for (List<String> row : list1) {
            writer.writeRow(row);
        }

        writer.writeRow(new ArrayList<>());

        writer.writeRow(Arrays.asList(row2));
        List<OutData2> data2List = resultData.getData2List();
        for (OutData2 outData2 : data2List) {
            ArrayList<String> row = new ArrayList<String>() {{
                add(String.valueOf(data1List.indexOf(outData2.getOutData1())));
                add(outData2.getFangxiang());
                add(outData2.getFenxiBizhong());
                add(outData2.getYuanbi());
                add(outData2.getZuzhiBenbi());
                add(outData2.getJituanBenbi());
                add(outData2.getQuanjuBenbi());
                add(outData2.getNeibuDanwei());
                add(outData2.getXjllName());
                add(outData2.getXjllCode());
            }};
            writer.writeRow(row);
        }

        writer.close();
    }

    private static boolean isEmpty(Object cell){
        return cell==null||cell.toString().equals("");
    }

    private static String getStr(Object cell){
        return cell==null?"":cell.toString();
    }

    private static Double getDouble(Object cell){
        String str = getStr(cell);
        try {
            Double num = Double.valueOf(str);
            return num;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static ResultData change(List<Indata> inDataList){
        List<OutData1> outData1List = new ArrayList<>();
        List<OutData2> outData2List = new ArrayList<>();
        if(inDataList==null||inDataList.size()==0)
            return null;


        for (int i = 1; i <= inDataList.size(); i++) {
            Indata indata = inDataList.get(i - 1);
            OutData1 outdataAll = getOutdata(indata);
            outData1List.add(outdataAll);
            // 凭证号
            for (DetailData detailData : indata.getDetailList()) {
                ResultData resultData = getOutdata(detailData);
                outData1List.addAll(resultData.getData1List());
                outData2List.addAll(resultData.getData2List());
            }
        }
        return new ResultData(outData1List,outData2List);
    }

    private static OutData1 getOutdata(Indata indata){
        String moneyStr = formatNumber(indata.getAllMoney());
        return new OutData1(indata.getId(), getToday(), getZhaiyao(indata), KMBM,
                moneyStr, moneyStr, null, null,
                new ArrayList<String>() {{
                    add(getFuzhuFormatStr("银行账户", YHZH));
                    add(getFuzhuFormatStr("银行存款辅助", YHCKFZ));
                }});
    }

    // 单条生成多条
    private static ResultData getOutdata(DetailData detailData) {
        List<OutData1> resultList = new ArrayList<>();
        List<OutData2> resultList2 = new ArrayList<>();
        Indata indata = detailData.getIndata();
        User user = userList.get("name", indata.getUserName()).get(0);
        Money money = moneyList.get("name", detailData.getType()).get(0);
        // 金额
        Double moneyNum = detailData.getMoney();
        double texValue = 0;

        double value = moneyNum - texValue;
        String valueNum = formatNumber(value);
        OutData1 data = new OutData1(indata.getId(), getToday(), getZhaiyao(detailData), money.getTexId()
                , null, null, valueNum, valueNum, new ArrayList<String>(){{
            add(getFuzhuFormatStr("客商",user.getId()));
        }});

        resultList.add(data);

        OutData2 outData2 = new OutData2(valueNum, valueNum, money.getXjllName(), money.getXjllCode());
        outData2.setOutData1(data);
        resultList2.add(outData2);

        if(money.getSplitFlag()){
            texValue += moneyNum * money.getTexPercent() / 100;
            String texNum = formatNumber(texValue);
            OutData1 texData = new OutData1(indata.getId(), getToday(), getZhaiyao(detailData), money.getTexId()
                    , null, null, texNum, texNum, new ArrayList<String>(){{
                add(getFuzhuFormatStr("税率",money.getTexName()));
                add(getFuzhuFormatStr("应税项目",money.getTexTypeCode()));
            }});
            OutData2 texData2 = new OutData2(texNum, texNum, money.getXjllName(), money.getXjllCode());
            texData2.setOutData1(texData);

            resultList.add(texData);
            resultList2.add(texData2);
        }else if("物业费".equals(money.getName())){
            Brand brand = brandList.get("name", detailData.getBrandName()).get(0);
            data.getFuzhuHesuanList().add(getFuzhuFormatStr("项目对象",brand.getId()));
            data.getFuzhuHesuanList().add(getFuzhuFormatStr("预收账款类型",YSZKLX));
            data.getFuzhuHesuanList().add(getFuzhuFormatStr("到期年度",DQND));
        }


        return new ResultData(resultList,resultList2);
    }

    private static String getFuzhuFormatStr(String name,String value){
        return String.format("%s:%s",value,name);
    }

    private static String getZhaiyao(DetailData detailData){
        Indata indata = detailData.getIndata();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        return String.format("%s 收到%s%s%s：%s元"
                , dateFormat.format(indata.getDate())
                , indata.getUserName()
                , detailData.getTimeDesc()
                , detailData.getType()
                , detailData.getMoney()
        );
    }

    private static String getZhaiyao(Indata indata){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String head = String.format("%s 收到%s", dateFormat.format(indata.getDate()), indata.getUserName());
        StringBuilder sb = new StringBuilder(head);
        for (DetailData detailData : indata.getDetailList()) {
            String detail = String.format("%s%s：%s元，"
                    , detailData.getTimeDesc()
                    , detailData.getType()
                    , detailData.getMoney());
            sb.append(detail);
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private static List<List<String>> getRowList(List<DataAfterChange> dataAfterChangeList) {
        List<List<String>> rowList = new ArrayList<>();
        String[] tableHead = {"* 核算账簿","* 凭证类别编码","* 凭证号","* 制单人编码","* 制单日期","* 摘要","* 科目编码","* 币种","* 原币借方金额","* 本币借方金额","* 原币贷方金额","* 本币贷方金额","辅助核算"};
        for (DataAfterChange data : dataAfterChangeList) {
            List<String> row = new ArrayList<>();
//            row.add();
        }


        return null;
    }

    private static String getToday(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    private static String formatNumber(Double num){
        DecimalFormat df = new DecimalFormat("0.00");
        BigDecimal bigDecimal = new BigDecimal(num).setScale(2, RoundingMode.HALF_UP);
        return df.format(bigDecimal.doubleValue());
    }

    private static Double formatNumberDouble(Double num){
        return new BigDecimal(num).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
