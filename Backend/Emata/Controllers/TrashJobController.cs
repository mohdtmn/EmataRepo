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
        public IEnumerable<string> Get()
        {

            int max = myDb.Trashes.Count();
            Random random = (new Random());
            Trash tr =  myDb.Trashes.Find((int) (random.NextDouble() * (max-1)) + 1);
            return new string[] { "" + tr.Id, "" + tr.Xloc, "" + tr.Yloc };
        }

        // GET api/<controller>/5
        //public string Get()
        //{
        //    return " ";
        //}

        // POST api/<controller>
        public void Post([FromBody]string value)
        {
        }

        // PUT api/<controller>/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/<controller>/5
        public void Delete(int id)
        {
        }
    }
}