using Emata.Context;
using Emata.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Emata.Controllers
{
    public class CleanReportController : ApiController
    {
        // GET api/<controller>
        public string Get(Double x, Double y, int Type)
        {
            HeatPoint hp = new HeatPoint();
            EmataContext myDb = new EmataContext();
            hp.Xloc = x;
            hp.Yloc = y;
            hp.Type = Type;
            hp.instant = DateTime.Now;
            myDb.HeatPoints.Add(hp);
            myDb.SaveChanges();
            return "Thanks !";
        }
        
    }
}