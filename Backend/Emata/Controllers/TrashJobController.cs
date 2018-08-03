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
    public class TrashJobController : ApiController
    {

        EmataContext myDb = new EmataContext();

        // GET api/<controller>
        public IEnumerable<string> Get(int DriverId)
        {
            Trash tr = getTrashForDriver(DriverId);
            return new string[] { "" + tr.Id, "" + tr.Xloc, "" + tr.Yloc };
        }

        private Trash getTrashForDriver(int DriverId)
        {
            //Machine learning takes place here
            //Inputs = Driver Location , Trash Cans Locations, Trash Cans Fill Level, Trash Cans Expected Fill Level Based on History
            //Output = Trash can location for the driver to attend
            int max = myDb.Trashes.Count();
            Random random = (new Random());
            return myDb.Trashes.Find((int)(random.NextDouble() * (max - 1)) + 1);
        }
        
    }
}