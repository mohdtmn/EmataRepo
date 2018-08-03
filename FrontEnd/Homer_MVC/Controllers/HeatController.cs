using Homer_MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Homer_MVC.Controllers
{
    public class HeatController : Controller
    {
        //

        Emata db = new Emata();
        // GET: /Heat/

        public ActionResult Index()
        {
            IList<HeatPoint> heatPoints = db.HeatPoints.ToList();
            string heater = "";
            
            foreach (var heatPoint in heatPoints)
            {
               
                heater += heatPoint.Xloc + ",";
                heater += heatPoint.Yloc + ",";
                heater += heatPoint.Type + ";";

            }

            
            ViewBag.Heater = heater;
            return View();
        }

    }
}
