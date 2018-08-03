using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Mvc.Html;
using System.Data.Entity;
using Homer_MVC.Models;

namespace Homer_MVC.Controllers
{
    public class MainController : Controller
    {
        Emata db = new Emata();

        public ActionResult Index()
        {
            IList<Trash> trashes = db.Trashes.ToList();

            string markers = "";
            double red = 0;
            double orange = 0;
            double green = 0;
           foreach (Trash trash in trashes)
            {
                if (trash.Level < 25)
                    green++;
                else if (trash.Level > 25 && trash.Level < 75)
                    orange++;
                else if (trash.Level > 75)
                    red++;
                markers += trash.Xloc + ",";
                markers += trash.Yloc + ",";
                markers += trash.Level + ";";

            }

            int  greenTrash = (int)((green /(red + green + orange)) * 100);
            int  redTrash =  (int)((red / (red + green + orange)) * 100);
            int orangeTrash = (int)((orange / (red + green + orange)) * 100);

            ViewBag.red = redTrash;
            ViewBag.green = greenTrash;
            ViewBag.orange = orangeTrash;
            ViewBag.Markers = markers;

           
            return View();
        }

        public ActionResult Page2()
        {
            return View();
        }

    }
}
