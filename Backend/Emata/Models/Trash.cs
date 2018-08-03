using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Emata.Models
{
    public class Trash : DbContext
    {
        public int Id { get; set; }
        public double Xloc { get; set; }
        public double Yloc { get; set; }
        public int Level { get; set; }
    }
}