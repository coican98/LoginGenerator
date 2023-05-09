package br.com.tjrs.util;

import Thor.API.Operations.tcUserOperationsIntf;
import Thor.API.tcResultSet;

import com.thortech.util.logging.Logger;

import com.thortech.xl.dataaccess.tcDataProvider;
import com.thortech.xl.dataaccess.tcDataSet;
import com.thortech.xl.dataaccess.tcDataSetException;
import com.thortech.xl.dataobj.util.XLDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import oracle.iam.platform.entitymgr.vo.SearchCriteria;


import oracle.iam.platform.Platform;


public class LoginGeneratorOld {
    public LoginGeneratorOld() {
        super();
    }
    private static Logger logger = Logger.getLogger("TJRS.UTIL.USERLOGIN");
    public static String DefineUserID (String fullName, List<String> ignored, String userType) {
            logger.info("Generating User Login");
            
            tcUserOperationsIntf moUserUtility = null;
            
            
            boolean existe = true;
            fullName = sanitizeString(fullName).toUpperCase();                    
            fullName = fullName.replaceAll("'", "");
            final List<String> IGNORED = ignored;
            String[] names = fullName.split(" ");
                        
            logger.info("Full Name : "+fullName);
            logger.info("User Type : "+userType);
            try{            
                    moUserUtility = Platform.getService(tcUserOperationsIntf.class); 
                    int i = names.length;
                    StringBuilder sb = new StringBuilder();

                    for (i = 0; i < names.length; i++){
                            if (!IGNORED.contains(names[i])){
                                    System.out.println(names[i].toString());
                                    sb.append(names[i]);
                            }
                    }
                    
                    if (userType.equalsIgnoreCase("Terceiro")){ // Terceiros
                            String UserID = names[0].substring(0, 1) + "." + names[names.length - 1];
                            logger.debug("DefineUserId: return: " + UserID);
                            
                            if (!userLoginExists(UserID)) {
                                    logger.debug("DefineUserId: return: " + UserID);
                                    existe = false;
                                    return UserID;
                            }
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0].substring(0, 1) + "." + names[1].substring(0, 1) + names[names.length - 1];
                                    }
                                    if (!userLoginExists(UserID)) {
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0] + names[names.length - 1];
                                    if (!userLoginExists(UserID)) {
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0] + "." + names[names.length - 1].substring(0, 1);
                                    if (!userLoginExists(UserID)) {                                             
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0] + "." + names[1].substring(0, 1) + names[names.length - 1];
                                    }
                                    if (!userLoginExists(UserID)){
                                            if (i >= 3){
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = false;
                                                    return UserID;
                                            }
                                    }
                            }
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0] + "." + names[1].substring(0, 1);
                                    }
                                    if (!userLoginExists(UserID)){
                                            if (i >= 3){
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = false;
                                                    return UserID;
                                            }
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0].substring(0,1) + "." + names[names.length - 1];
                                    if (!userLoginExists(UserID)){
                                            for (int j=2; existe == true; j++){
                                                    UserID = names[0].substring(0,j) + "." + names[names.length - 1];
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = userLoginExists(UserID);
                                            }
                                            return UserID;
                                    }
                            }
                    } else { // Servidores
                            String UserID = names[0].substring(0, 1) + names[names.length - 1];
                            
                            if (!userLoginExists(UserID)) {
                                    logger.debug("DefineUserId: return: " + UserID);
                                    existe = false;
                                    return UserID;
                            } 
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0].substring(0, 1) + names[1].substring(0, 1) + names[names.length - 1];
                                    }
                                    if (!userLoginExists(UserID)) {
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0] + names[names.length - 1];
                                    if (!userLoginExists(UserID)) {
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0] + names[names.length - 1].substring(0, 1);
                                    if (!userLoginExists(UserID)) {     
                                            logger.debug("DefineUserId: return: " + UserID);
                                            existe = false;
                                            return UserID;
                                    }
                            }
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0] + names[1].substring(0, 1) + names[names.length - 1];
                                    }
                                    if (!userLoginExists(UserID)){
                                            if (i >= 3){
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = false;
                                                    return UserID;
                                            }
                                    }
                            }
                            
                            if (existe){
                                    if (i >= 3){
                                            UserID = names[0] + names[1].substring(0, 1);
                                    }
                                    if (!userLoginExists(UserID)){
                                            if (i >= 3){
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = false;
                                                    return UserID;
                                            }
                                    }
                            }
                            
                            if (existe){
                                    UserID = names[0].substring(0,1) + names[names.length - 1];
                                    if (userLoginExists(UserID)){
                                            for (int j=2; existe == true; j++){
                                                    UserID = names[0].substring(0,j) + names[names.length - 1];
                                                    logger.debug("DefineUserId: return: " + UserID);
                                                    existe = userLoginExists(UserID);
                                            }
                                            return UserID;
                                    }
                            }
                    }
                    
                    return null;
            }       
            catch(Exception e) {
                    logger.error("DefineUserID: Exception", e);
                    return null;
            }
            finally {
                    if (moUserUtility != null) moUserUtility.close();
            }
    }

    /*******************************************************
     * Verifica se o login ja existe no OIM 
     * @param userId login criado
     * @param moUserUtility
     * @see Houve a necessidade de alterar a query de busca por usuarios, pois o plugin da API Thor findAllUsers esta descontinuado;
     * @return boolean
     ******************************************************/
     private static boolean userLoginExists(String userId) {

             HashMap<String, String> attrs = new HashMap<String, String>();
             attrs.put("Users.User ID", userId);
             tcDataProvider dataProvider = XLDatabase.getInstance().getDataBase();
             tcDataSet ds1 = new tcDataSet();
             ds1.setQuery(dataProvider,"select usr_login from usr where USR_LOGIN = '"+userId+"'");
             try {
                 ds1.executeQuery();
                 if(!ds1.isEmpty()){
                     logger.debug("Usu�rio existente: "+ds1.getString("USR_LOGIN"));
                     return true;
                 }
             } catch (tcDataSetException e) {
                 logger.info("Erro "+e);
             }
                  return false;    
         
         }
    
    /*
     * Metodo anterior a 20-03-2018
    private static boolean userIdExists(String userId, tcUserOperationsIntf moUserUtility) throws Exception {
            HashMap<String, String> attrs = new HashMap<String, String>();
            attrs.put("Users.User ID", userId);
            tcResultSet moResultSet = moUserUtility.findAllUsers(attrs);
            boolean userFound = (moResultSet.getRowCount() > 0);
            return userFound;
    }
     */
    private static List<String> romanNumbers = Arrays.asList(new String[] {"II","III","IV","VI","VII","VIII","IX"});
            
            /*******************************************************
             * Remove acentuacao grafica 
             * @param input string para remocao
             * 
             * @return string
             ******************************************************/
            public static String sanitizeString(String input) {
                    logger.debug("sanitizeString: input=" + input);
                    // Accented characters to normal characters
                    input = input.replaceAll("[�����]", "a");
                    input = input.replaceAll("[�����]", "A");
                    input = input.replaceAll("[����]", "e");
                    input = input.replaceAll("[����]", "E");
                    input = input.replaceAll("[�?��]", "i");
                    input = input.replaceAll("[�?��]", "I");
                    input = input.replaceAll("[����]", "o");
                    input = input.replaceAll("[����]", "O");
                    input = input.replaceAll("[���?]", "u");
                    input = input.replaceAll("[���?]", "U");
                    input = input.replaceAll("[��]", "y");
                    input = input.replaceAll("[ݟ]", "Y");
                    input = input.replaceAll("[�]", "c");
                    input = input.replaceAll("[�]", "C");

                    // Remove special characters
                    {
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < input.length(); i++) {
                                    if (Character.isLetterOrDigit(input.charAt(i)) || input.charAt(i) == ' ' || input.charAt(i) == '_') {
                                            sb.append(input.charAt(i));
                                    }
                            }
                            input = sb.toString();
                    }

                    StringTokenizer st = new StringTokenizer(input, " ", true);
                    StringBuffer sb = new StringBuffer(input.length());
                    while (st.hasMoreTokens()) {
                            String token = st.nextToken();
                            if (romanNumbers.contains(token)) {
                                    sb.append(token);
                            } else {
                                    sb.append(token.substring(0, 1).toUpperCase());
                                    sb.append(token.substring(1).toLowerCase());
                            }
                    }
                    logger.debug("sanitizeString: return: " + sb.toString());
                    return sb.toString();
            }
}
