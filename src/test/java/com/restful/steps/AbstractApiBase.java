package com.restful.steps;

import com.restful.steps.common.CommonData;
import com.restful.steps.common.CommonDataProvider;
import com.restful.client.RestfulApiRestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractApiBase {
  protected static final String todayDate = LocalDate.now()
      .format(DateTimeFormatter.ISO_LOCAL_DATE);
  protected static final String yesterdayDate = LocalDate.now().minusDays(1)
      .format(DateTimeFormatter.ISO_LOCAL_DATE);
  protected static final String tomorrowDate = LocalDate.now().plusDays(1)
      .format(DateTimeFormatter.ISO_LOCAL_DATE);
  protected static final String dayAfterTomorrowDate = LocalDate.now().plusDays(2)
      .format(DateTimeFormatter.ISO_LOCAL_DATE);

  protected final CommonData commonData;
  protected final RestfulApiRestClient restfulApiRestClient;




  public AbstractApiBase() {
    this.commonData = CommonDataProvider.get(Thread.currentThread().getId());
    this.restfulApiRestClient = commonData.getRestfulApiRestClient();

  }
}
