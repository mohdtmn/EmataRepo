using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Emata.Models
{
    public class HeatPoint : DbContext
    {
        public int Id { get; set; }
        public double Xloc { get; set; }
        public double Yloc { get; set; }
        public int Type { get; set; }
        public DateTime instant { get; set; }
    }
}