package controllers

import fabricator.enums.DateFormat
import play.api.mvc.Controller

class ControllerBase extends Controller{

   def matchFormat(stringFormat: String): DateFormat = {
    stringFormat match {
      case _: String if stringFormat.equals(DateFormat.d_MMM_SPACE.getFormat) => DateFormat.d_MMM_SPACE
      case _:String if stringFormat.equals(DateFormat.dd.getFormat) => DateFormat.dd
      case _:String if stringFormat.equals(DateFormat.dd_MM.getFormat) => DateFormat.dd_MM
      case _:String if stringFormat.equals(DateFormat.dd_MM_yy.getFormat) => DateFormat.dd_MM_yy
      case _:String if stringFormat.equals(DateFormat.dd_MM_yy_HH_mm.getFormat) => DateFormat.dd_MM_yy_HH_mm
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy.getFormat) => DateFormat.dd_MM_yyyy
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_SEMICOLON.getFormat) => DateFormat.dd_MM_yyyy_SEMICOLON
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_HH_mm.getFormat) => DateFormat.dd_MM_yyyy_HH_mm
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_HH_mm_ss.getFormat) => DateFormat.dd_MM_yyyy_HH_mm_ss
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_H_m_s.getFormat) => DateFormat.dd_MM_yyyy_H_m_s
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_H_m_s_a.getFormat) => DateFormat.dd_MM_yyyy_H_m_s_a
      case _:String if stringFormat.equals(DateFormat.dd_mm_yyyy_SEMICOLON.getFormat) => DateFormat.dd_mm_yyyy_SEMICOLON
      case _:String if stringFormat.equals(DateFormat.mm_dd_yyyy_SEMICOLON.getFormat) => DateFormat.mm_dd_yyyy_SEMICOLON
      case _:String if stringFormat.equals(DateFormat.dd_MM_YYYY_SEMICOLON.getFormat) => DateFormat.dd_MM_YYYY_SEMICOLON
      case _:String if stringFormat.equals(DateFormat.dd_MM_YYYY_BACKSLASH.getFormat) => DateFormat.dd_MM_YYYY_BACKSLASH
      case _:String if stringFormat.equals(DateFormat.dd_MM_YY_BACKSLASH.getFormat) => DateFormat.dd_MM_YY_BACKSLASH
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_DOT.getFormat) => DateFormat.dd_MM_yyyy_DOT
      case _:String if stringFormat.equals(DateFormat.dd_M_yyyy_DOT.getFormat) => DateFormat.dd_M_yyyy_DOT
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_HH.getFormat) => DateFormat.dd_MM_yyyy_HH
      case _:String if stringFormat.equals(DateFormat.dd_MM_yyyy_HH_SEMICOLON.getFormat) => DateFormat.dd_MM_yyyy_HH_SEMICOLON
      case _:String if stringFormat.equals(DateFormat.d_MMM_SPACE.getFormat) => DateFormat.d_MMM_SPACE
      case _:String if stringFormat.equals(DateFormat.dd_MMMM_yyyy_SPACE.getFormat) => DateFormat.dd_MMMM_yyyy_SPACE
      case _:String if stringFormat.equals(DateFormat.HH_mm.getFormat) => DateFormat.HH_mm
      case _:String if stringFormat.equals(DateFormat.MM_yy.getFormat) => DateFormat.MM_yy
      case _:String if stringFormat.equals(DateFormat.MM_yyyy.getFormat) => DateFormat.MM_yyyy
      case _:String if stringFormat.equals(DateFormat.yyyy_MM_dd.getFormat) => DateFormat.yyyy_MM_dd
      case _:String if stringFormat.equals(DateFormat.yyMMdd.getFormat) => DateFormat.yyMMdd
      case _:String if stringFormat.equals(DateFormat.yyyyMMdd.getFormat) => DateFormat.yyyyMMdd
      case _:String if stringFormat.equals(DateFormat.yyyyMMddHHmm.getFormat) => DateFormat.yyyyMMddHHmm
      case _ => DateFormat.dd_MM_yyyy
    }
  }

}
