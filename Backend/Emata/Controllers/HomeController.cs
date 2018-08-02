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
            ViewBag.Title = "Home Page";
            return View();
        }

        public void createTrashes()
        {
            Random rn = new Random();
            for (int i = 0; i < 500; i++)
            {
                Trash tr = myDb.Trashes.Find(i);
                if (tr == null)
                {
                    tr = new Trash();
                    myDb.Trashes.Add(tr);
                }
                tr.Xloc = 21.412294 + rn.NextDouble() * 0.01;
                tr.Yloc = 39.890611 + rn.NextDouble() * 0.01;
                tr.Level = (int)(rn.NextDouble() * 25);
            }
            for (int i = 500; i < 600; i++)
            {
                Trash tr = myDb.Trashes.Find(i);
                if (tr == null)
                {
                    tr = new Trash();
                    myDb.Trashes.Add(tr);
                }
                tr.Xloc = 21.412294 + rn.NextDouble() * 0.01;
                tr.Yloc = 39.890611 + rn.NextDouble() * 0.01;
                tr.Level = (int)(rn.NextDouble() * 25) + 30;
            }
            for (int i = 600; i < 650; i++)
            {
                Trash tr = myDb.Trashes.Find(i);
                if (tr == null)
                {
                    tr = new Trash();
                    myDb.Trashes.Add(tr);
                }
                tr.Xloc = 21.412294 + rn.NextDouble() * 0.01;
                tr.Yloc = 39.890611 + rn.NextDouble() * 0.01;
                tr.Level = (int)(rn.NextDouble() * 30) + 70;
            }
            myDb.SaveChanges();

        }
    }
}