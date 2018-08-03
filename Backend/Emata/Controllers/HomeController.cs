using Emata.Context;
using Emata.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Emata.Controllers
{
    public class HomeController : Controller
    {
        EmataContext myDb = new EmataContext();

        public ActionResult Index()
        {
            //Data simulation
            //createTrashes();
            //createHeatPoints();
            ViewBag.Title = "Home Page";
            return View();
        }

        public void createTrashes()
        {
            Random rn = new Random();
            for (int i = 0; i < 725; i++)
            {
                Trash tr = myDb.Trashes.Find(i);
                if (tr == null)
                {
                    tr = new Trash();
                    myDb.Trashes.Add(tr);
                }
                tr.Xloc = 21.406797 + rn.NextDouble() * 0.04 * (rn.NextDouble() > 0.5 ? 1 : -1);
                tr.Yloc = 39.879742 + rn.NextDouble() * 0.04 * (rn.NextDouble() > 0.5 ? 1 : -1);

                if (i < 500)
                    tr.Level = (int)(rn.NextDouble() * 25);
                else if (i < 600)
                    tr.Level = (int)(rn.NextDouble() * 25) + 30;
                else if (i < 700)
                    tr.Level = (int)(rn.NextDouble() * 30) + 70;
                else
                    tr.Level = -1;
            }
            myDb.SaveChanges();

        }

        void createHeatPoints()
        {
            createHeatLine(21.417237, 39.892667, 4, 0.3, 0);
            createHeatLine(21.415502, 39.888893, 3, 0.8, 0);
            createHeatLine(21.417343, 39.885483, 7, 1, 0);
            createHeatLine(21.413724, 39.885551, 9, 1, 1);
            createHeatLine(21.409487, 39.885927, 3, 2, 1);
            createHeatLine(21.407044, 39.892145, 11, 0.5, 1);
            createHeatLine(21.401310, 39.895836, 9, 0.7, 1);
            createHeatLine(21.406287, 39.886531, 6, 3, 1);
            myDb.SaveChanges();
        }

        void createHeatLine(double startX, double startY, int len, double slope, int type)
        {
            for (int i = 0; i < len; i++)
            {
                HeatPoint hp = new HeatPoint();
                hp.Xloc = startX + 0.001 * i;
                hp.Yloc = startY + 0.001 * i * slope;
                hp.instant = DateTime.Now;
                hp.Type = type;
                myDb.HeatPoints.Add(hp);
            }
        }
    }
}