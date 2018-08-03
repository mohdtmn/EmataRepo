using Homer_MVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Homer_MVC.Controllers
{
    public class CarsController : Controller
    {
        //
        Emata db = new Emata();

        // GET: /Cars/

        public ActionResult Index()
        {
            IList<Trash> trashes = db.Trashes.ToList();

            string markers = "";
            double notClean = 0;
            double blue = 0;
            foreach (Trash trash in trashes)
            {
                if (trash.Level > 25)
                    notClean++;
                if (trash.Level < 0)
                    blue++;
                markers += trash.Xloc + ",";
                markers += trash.Yloc + ",";
                markers += trash.Level + ";";

            }

            int totalNotClean = (int)((notClean / trashes.Count()) * 100);
            int totalBlue = (int)((blue / trashes.Count()) * 100);
            ViewBag.total = totalNotClean;
            ViewBag.blue = totalBlue;
            ViewBag.Markers = markers;

            return View();
        }

    }
}
