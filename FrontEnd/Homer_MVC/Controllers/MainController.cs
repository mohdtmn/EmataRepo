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

            return View(db.Trashes.ToList());
        }

        public ActionResult Page2()
        {
            return View();
        }

    }
}
